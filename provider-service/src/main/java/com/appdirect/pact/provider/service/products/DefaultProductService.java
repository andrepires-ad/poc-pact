package com.appdirect.pact.provider.service.products;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import com.appdirect.pact.provider.api.model.ProductWsDTO;

public class DefaultProductService implements ProductService {

	@Override
	public List<ProductWsDTO> getProducts(List<String> ids) {
		if (ids == null) {
			return Collections.emptyList();
		}
		return ids.stream()
				.map(id -> new ProductWsDTO().id(id).name("Product " + id))
				.collect(toList());
	}
}
