package arda.persistence.jpa;

import arda.entities.Warehouse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class WarehousesDAO {
    @Inject
    private EntityManager em;

    public void persist(Warehouse warehouse){
        this.em.persist(warehouse);
    }

    public List<Warehouse> loadAll(){
        return em.createNamedQuery("Warehouse.findAll", Warehouse.class).getResultList();
    }

    public Warehouse findOne(Integer id) {
        return em.find(Warehouse.class, id);
    }
}
