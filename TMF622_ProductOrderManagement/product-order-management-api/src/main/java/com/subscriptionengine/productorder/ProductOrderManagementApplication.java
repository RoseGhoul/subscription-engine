package com.subscriptionengine.productorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ProductOrderManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductOrderManagementApplication.class, args);
    }
}
