package arda.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "select i from Item as i")
})
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max=50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "WEIGHT", nullable = false)
    private Float weight;

    @ManyToOne
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse;

    @ManyToMany(mappedBy = "itemList")
    private List<Supplier> supplierList = new ArrayList<>();

    @Version
    @Column(name="VERSION")
    private Integer version;
}