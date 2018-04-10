package com.appdirect.pact.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.appdirect.pact.provider.service.products.DefaultProductService;
import com.appdirect.pact.provider.service.products.PactProductService;
import com.appdirect.pact.provider.service.products.ProductService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    private static final String PACT_TEST = "PactTest";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ProductService getProductService() {
        if (System.getProperty(PACT_TEST) != null && System.getProperty(PACT_TEST).equals("true")) {
            return new PactProductService();
        }
        return new DefaultProductService();
    }
}
