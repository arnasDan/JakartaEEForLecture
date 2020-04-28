package arda.persistence.jpa;

import arda.entities.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SuppliersDAO {
    @Inject
    private EntityManager em;

    public void persist(Supplier supplier){
        this.em.persist(supplier);
    }

    public Supplier findOne(Integer id){
        return this.em.find(Supplier.class, id);
    }

    public List<Supplier> loadAll(){
        return em.createNamedQuery("Supplier.findAll", Supplier.class).getResultList();
    }
}
