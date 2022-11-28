package xyz.mynt.wcbootcamp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductsDTO {

    List<ProductDTO> products;

//    ProductDTO productDTO;
}
