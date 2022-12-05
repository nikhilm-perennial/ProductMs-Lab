package xyz.mynt.wcbootcamp.entity;

import lombok.*;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private BigDecimal price;

    private Integer inStock;

    private Integer reservedQuantity;

    private Boolean available;
}
