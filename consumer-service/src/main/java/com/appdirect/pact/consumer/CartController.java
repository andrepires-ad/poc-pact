package com.appdirect.pact.consumer;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.pact.consumer.apiclients.ProductsApi;
import com.appdirect.pact.consumer.apiclients.ProductWsDTO;
import com.appdirect.pact.consumer.model.CartWsDTO;

@RestController
public class CartController {

	@Autowired
	private ProductsApi productsApi;

	@RequestMapping(value = "/cart",
			produces = { "application/json" },
			consumes = { "application/json" },
			method = RequestMethod.GET)
	public ResponseEntity<?> getProducts() {
		try {
			return ResponseEntity.ok(
					productsApi.getProducts(
								asList("1", "2", "3").stream().collect(Collectors.joining(",")))
							.execute().body()
					.stream()
					.map(product -> new CartWsDTO(product, 1))
					.collect(Collectors.toList())
			);
		} catch (IOException e) {
			// handle exception
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("{\"error\": \"" + e.getMessage() + "\" }");
		}

	}
}
