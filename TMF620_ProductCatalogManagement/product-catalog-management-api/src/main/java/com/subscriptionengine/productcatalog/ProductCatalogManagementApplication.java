package com.subscriptionengine.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductCatalogManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogManagementApplication.class, args);
    }
}
