package xyz.mynt.wcbootcamp.utility;

import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.dto.ReservedProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;

import java.math.RoundingMode;

public class MapperUtils {

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory().toString())
                .price(productEntity.getPrice().setScale(2, RoundingMode.CEILING))
                .stock(productEntity.getInStock())
                .reservedQuantity(productEntity.getReservedQuantity())
                .available(productEntity.getAvailable())
                .build();
    }

    public static ReservedProductDTO reservedProductDTO(ProductEntity productEntity){
        return ReservedProductDTO.builder()
                .name(productEntity.getName())
                .stock(productEntity.getInStock())
                .reservedQuantity(productEntity.getReservedQuantity())
                .available(productEntity.getAvailable())
                .build();


    }

    private MapperUtils() {}
}
