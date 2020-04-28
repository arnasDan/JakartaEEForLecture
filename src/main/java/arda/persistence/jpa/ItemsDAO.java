package arda.persistence.jpa;

import arda.entities.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ItemsDAO {
    @Inject
    private EntityManager em;

    public void persist(Item item){
        this.em.persist(item);
    }

    public Item findOne(Integer id){
        return this.em.find(Item.class, id);
    }

    public List<Item> loadAll(){
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }

    public Item update(Item item){
        return em.merge(item);
    }
}
