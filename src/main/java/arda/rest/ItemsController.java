package arda.rest;

import arda.entities.Item;
import arda.interceptors.LoggedInvocation;
import arda.persistence.jpa.ItemsDAO;
import arda.persistence.jpa.WarehousesDAO;
import arda.rest.contracts.ItemDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/items")
public class ItemsController {
    @Inject
    @Getter
    @Setter
    private ItemsDAO itemsDAO;

    @Inject
    @Getter
    @Setter
    private WarehousesDAO warehousesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @LoggedInvocation
    public Response getById(@PathParam("id") final Integer id) {
        var item = itemsDAO.findOne(id);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var itemDto = new ItemDTO();

        itemDto.setName(item.getName());
        itemDto.setWeight(item.getWeight());
        itemDto.setWarehouseId(item.getWarehouse().getId());

        return Response.ok(itemDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @LoggedInvocation
    public Response add(ItemDTO itemDto) {
        var item = new Item();
        item.setName(itemDto.getName());
        item.setWeight(itemDto.getWeight());

        if (!updateEntity(itemDto, item))
            return Response.status(Response.Status.BAD_REQUEST).build();

        itemsDAO.persist(item);

        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @LoggedInvocation
    public Response update(@PathParam("id") final Integer playerId, ItemDTO itemDto) throws InterruptedException {
        try {
            var existingItem = itemsDAO.findOne(playerId);
            if (existingItem == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (!updateEntity(itemDto, existingItem))
                return Response.status(Response.Status.BAD_REQUEST).build();

            Thread.sleep(5000);

            itemsDAO.update(existingItem);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    private boolean updateEntity(ItemDTO itemDto, Item existingItem) {
        var warehouse = warehousesDAO.findOne(itemDto.getWarehouseId());
        if (warehouse == null) {
            return false;
        }
        existingItem.setWarehouse(warehouse);
        existingItem.setWeight(itemDto.getWeight());
        existingItem.setName(itemDto.getName());
        return true;
    }
}