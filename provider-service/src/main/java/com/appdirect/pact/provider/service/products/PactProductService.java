package com.appdirect.pact.provider.service.products;

import java.util.Arrays;
import java.util.List;

import com.appdirect.provider.model.ProductWsDTO;

public class PactProductService implements ProductService {

	@Override
	public List<ProductWsDTO> getProducts() {
		return Arrays.asList(
				new ProductWsDTO().id("id-1").name("name-1"),
				new ProductWsDTO().id("id-2").name("name-2"),
				new ProductWsDTO().id("id-3").name("name-3")
		);
	}
}
