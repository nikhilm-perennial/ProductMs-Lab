package xyz.mynt.wcbootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductMsLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMsLabApplication.class, args);
    }

}
