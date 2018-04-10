package com.appdirect.pact.provider.service.products;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.appdirect.provider.model.ProductWsDTO;

public class DefaultProductService implements ProductService {

	@Override
	public List<ProductWsDTO> getProducts() {
		return Arrays.asList(
				new ProductWsDTO().id("1").name("Sunscreen"),
				new ProductWsDTO().id("2").name("Summer chair")
		);
	}
}
