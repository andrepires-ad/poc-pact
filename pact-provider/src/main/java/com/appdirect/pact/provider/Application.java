package com.appdirect.pact.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<String> health() {
        return new ResponseEntity<String>("Product service is running Smoothly", HttpStatus.OK);
    }
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> products() {
        return new ResponseEntity<>(Arrays.asList(new Product(1), new Product(3)), HttpStatus.OK);
    }
}
