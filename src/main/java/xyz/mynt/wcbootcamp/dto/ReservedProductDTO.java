package xyz.mynt.wcbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservedProductDTO {

    private String name;

    @JsonProperty("in_stock")
    private Integer stock;

    @JsonProperty("reserved_quantity")
    private Integer reservedQuantity;

    @JsonProperty("is_available")
    private Boolean available;
}