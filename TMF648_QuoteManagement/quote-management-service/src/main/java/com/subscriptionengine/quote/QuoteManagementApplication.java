package com.subscriptionengine.quote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@org.springframework.data.jpa.repository.config.EnableJpaAuditing
public class QuoteManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteManagementApplication.class, args);
    }
}
