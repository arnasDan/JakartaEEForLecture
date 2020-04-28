package arda.useCases;

import arda.mybatis.dao.SupplierMapper;
import arda.mybatis.dao.ItemMapper;
import arda.mybatis.dao.WarehouseMapper;
import arda.mybatis.dao.ItemSupplierMapper;
import lombok.Getter;
import arda.mybatis.model.Item;
import arda.mybatis.model.Warehouse;
import arda.mybatis.model.Supplier;
import arda.mybatis.model.ItemSupplier;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.spi.Producer;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class MyBatisController {
    @Getter
    private final Warehouse warehouse = new Warehouse();
    @Getter
    private final Item item = new Item();
    @Getter
    private final Item itemToAssign = new Item();
    @Getter
    private final Supplier supplier = new Supplier();
    @Getter
    private final ItemSupplier itemsSupplier = new ItemSupplier();

    @Getter
    private List<Supplier> suppliers;
    @Getter
    private List<Item> items;
    @Getter
    private List<Warehouse> warehouses;

    @Inject
    private SupplierMapper supplierMapper;
    @Inject
    private ItemMapper itemMapper;
    @Inject
    private WarehouseMapper warehouseMapper;
    @Inject
    private ItemSupplierMapper itemsSupplierMapper;

    @PostConstruct
    public void init(){
        this.suppliers = supplierMapper.selectAll();
        this.items = itemMapper.selectAll();
        this.warehouses = warehouseMapper.selectAll();
    }

    @Transactional
    public void createNewItem(){
        item.setWarehouseId(warehouse.getId());
        itemMapper.insert(item);
    }

    @Transactional
    public void createNewSupplier(){
        supplierMapper.insert(supplier);
    }

    @Transactional
    public void createNewWarehouse(){
        warehouseMapper.insert(warehouse);
    }

    @Transactional
    public Warehouse getWarehouseById(Integer id) {
        return warehouseMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void assignItemToSupplier(){
        itemsSupplier.setItemId(itemToAssign.getId());
        itemsSupplier.setSupplierId(supplier.getId());
        itemsSupplierMapper.insert(itemsSupplier);
    }

    @Transactional
    public List<Supplier> getItemSuppliers(Integer itemId){
        var itemSupplierList = new ArrayList<Supplier>();
        var itemSuppliers = itemsSupplierMapper.selectAll();
        for (ItemSupplier is : itemSuppliers){
            if(is.getItemId().equals(itemId)){
                itemSupplierList.add(supplierMapper.selectByPrimaryKey(is.getSupplierId()));
            }
        }

        return itemSupplierList;
    }
}