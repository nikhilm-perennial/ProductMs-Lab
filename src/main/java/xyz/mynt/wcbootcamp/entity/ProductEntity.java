package xyz.mynt.wcbootcamp.entity;

import lombok.*;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private String id;

    private String name;

    private CategoryEnum category;

    private BigDecimal price;

    private Integer inStock;

    private Integer reservedQuantity;

    private Boolean available;
}
