package xyz.mynt.wcbootcamp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.repository.ProductRepository;
import xyz.mynt.wcbootcamp.service.ProductService;
import xyz.mynt.wcbootcamp.utility.MapperUtils;
import xyz.mynt.wcbootcamp.utility.NotificationSenderUtils;

import static xyz.mynt.wcbootcamp.utility.RandomGeneratorUtils.generateRandomUUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {

        ProductEntity productEntity = MapperUtils.toProductEntity(productDTO);
        productEntity.setId(generateRandomUUID());
        productEntity.setReservedQuantity(0);
        ProductDTO dto = MapperUtils.toProductDTO(productRepository.save(productEntity));
        kafkaTemplate.send("Product_Notification", NotificationSenderUtils.generateNotificationBody(productDTO));

        return productDTO;
    }
}