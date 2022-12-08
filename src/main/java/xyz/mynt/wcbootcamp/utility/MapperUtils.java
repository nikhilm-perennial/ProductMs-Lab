package xyz.mynt.wcbootcamp.utility;

import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;

public class MapperUtils {

    public static ProductDTO toProductDTO(ProductEntity productEntity){
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory().toString())
                .price(productEntity.getPrice())
                .stock(productEntity.getInStock())
                .reservedQuantity(productEntity.getReservedQuantity())
                .available(productEntity.getAvailable())
                .build();
    }

    public static ProductEntity toProductEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .category(CategoryEnum.valueOf(productDTO.getCategory()))
                .price(productDTO.getPrice())
                .inStock(productDTO.getStock())
                .reservedQuantity(productDTO.getReservedQuantity())
                .available(productDTO.getAvailable())
                .build();
    }
}
