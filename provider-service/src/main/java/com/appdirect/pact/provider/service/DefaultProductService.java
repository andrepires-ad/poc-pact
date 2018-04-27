package com.appdirect.pact.provider.service;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import com.appdirect.pact.provider.model.ProductDTO;

public class DefaultProductService implements ProductService {

	@Override
	public List<ProductDTO> getProducts(List<String> ids) {
		if (ids == null) {
			return Collections.emptyList();
		}
		return ids.stream()
				.map(id -> new ProductDTO().id(id).name("Product " + id))
				.collect(toList());
	}
}
