package xyz.mynt.wcbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDTO {

    private String id;

    private String name;

    private String category;

    private BigDecimal price;

    @JsonProperty("in_stock")
    private Integer stock;

    @JsonProperty("reserved_quantity")
    private Integer reservedQuantity;

    @JsonProperty("is_available")
    private Boolean available;
}
