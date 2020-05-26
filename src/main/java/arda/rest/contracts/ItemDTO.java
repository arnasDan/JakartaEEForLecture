package arda.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private String name;
    private Float weight;
    private int warehouseId;
}
