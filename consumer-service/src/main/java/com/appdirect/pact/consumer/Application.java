package com.appdirect.pact.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class Application {

    @Autowired
    ConsumerPort consumerPort;

    @RequestMapping("/products")
    public List<Product> products() {
        return consumerPort.products();
    }

    @RequestMapping("/")
    public String health() {
        return "Running Smoothly!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
