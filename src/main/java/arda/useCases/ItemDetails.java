package arda.useCases;

import arda.entities.Item;
import arda.interceptors.LoggedInvocation;
import arda.persistence.jpa.ItemsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
@SessionScoped
public class ItemDetails implements Serializable {
    @Inject
    private ItemsDAO itemsDao;

    //@Inject
    //@Production
    //IEarningsGenerator earningsGenerator;

    @Getter
    private Item item;

    @PostConstruct
    public void init() {
        var requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        var itemId = Integer.parseInt(requestParameters.get("itemId"));

        this.item = itemsDao.findOne(itemId);
    }

    @Transactional
    @LoggedInvocation
    public String save() {
        try {
            itemsDao.update(item);
        }
        catch (OptimisticLockException e) {
            return handleLockException();
        }

        return "/details?itemId=" + item.getId() + "&faces-redirect=true";
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String handleLockException() {
        this.item = itemsDao.findOne(this.item.getId());

        return "/details?itemId=" + item.getId() + "&faces-redirect=true&error=optimistic-lock";
    }

    //public String getEarnings() {
    //    return earningsGenerator.generateEarnings();
    //}
}

