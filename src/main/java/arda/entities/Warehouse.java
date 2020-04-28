package arda.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "WAREHOUSE")
@NamedQueries({
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT wh FROM Warehouse wh")
})
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "warehouse")
    private List<Item> itemList = new ArrayList<>();
}
