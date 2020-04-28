package arda.useCases;

import arda.entities.Supplier;
import arda.entities.Item;
import arda.entities.Warehouse;
import lombok.Getter;
import arda.persistence.jpa.WarehousesDAO;
import arda.persistence.jpa.ItemsDAO;
import arda.persistence.jpa.SuppliersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
@RequestScoped
public class PersistenceController implements Serializable {
    @Inject
    private WarehousesDAO warehousesDAO;
    @Inject
    private ItemsDAO itemsDAO;
    @Inject
    private SuppliersDAO suppliersDAO;

    @Getter
    private final Warehouse warehouse = new Warehouse();
    @Getter
    private final Item item = new Item();
    @Getter
    private final Item itemToAssign = new Item();
    @Getter
    private final Supplier supplier = new Supplier();

    @Getter
    private List<Warehouse> warehouses;
    @Getter
    private List<Item> items;
    @Getter
    private List<Supplier> suppliers;

    @PostConstruct
    public void init(){
        this.warehouses = warehousesDAO.loadAll();
        this.items = itemsDAO.loadAll();
        this.suppliers = suppliersDAO.loadAll();
    }

    @Transactional
    public void createNewWarehouse(){
        warehouse.setId(null);
        warehousesDAO.persist(warehouse);
    }

    @Transactional
    public void createNewItem(){
        List<Supplier> supplierList = new ArrayList<>();

        warehousesDAO.findOne(warehouse.getId());
        item.setWarehouse(warehouse);
        item.setSupplierList(supplierList);

        itemsDAO.persist(item);
    }

    @Transactional
    public void createNewSupplier(){
        List<Item> itemList = new ArrayList<>();

        supplier.setId(null);
        supplier.setItemList(itemList);

        suppliersDAO.persist(supplier);
    }

    @Transactional
    public void assignItemToSupplier(){
        Item itemToAssignInstance = itemsDAO.findOne(itemToAssign.getId());
        Supplier supplierToAssign = suppliersDAO.findOne(supplier.getId());

        List<Item> itemList = supplierToAssign.getItemList();
        itemList.add(itemToAssignInstance);

        supplierToAssign.setItemList(itemList);
    }
}
