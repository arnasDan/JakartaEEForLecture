package arda.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SUPPLIER")
@NamedQueries({
        @NamedQuery(name= "Supplier.findAll", query = "SELECT sp FROM Supplier sp")
})
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Column(name="NAME", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name="ITEM_SUPPLIER",
            joinColumns = {@JoinColumn(name="SUPPLIER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name="ITEM_ID", referencedColumnName = "ID")})
    private List<Item> itemList = new ArrayList();

    public Supplier() {

    }
}
