package xyz.mynt.wcbootcamp.entity;

import jakarta.persistence.*;
import lombok.*;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;

import java.math.BigDecimal;

import static jakarta.persistence.EnumType.STRING;


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

    @Enumerated(STRING)
    private CategoryEnum category;

    private BigDecimal price;

    private Integer inStock;

    private Integer reservedQuantity;

    private Boolean available;
}
