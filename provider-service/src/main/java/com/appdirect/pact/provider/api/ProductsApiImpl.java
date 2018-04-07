package com.appdirect.pact.provider.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.provider.api.ProductsApi;
import com.appdirect.provider.model.ProductWsDTO;

@RestController
public class ProductsApiImpl implements ProductsApi {

	@Override
	public ResponseEntity<List<ProductWsDTO>> getProducts() {
		return ResponseEntity.ok(
				Arrays.asList(
						new ProductWsDTO().id("1").name("Sunscreen"),
						new ProductWsDTO().id("2").name("Summer chair")
				)
		);
	}
}
