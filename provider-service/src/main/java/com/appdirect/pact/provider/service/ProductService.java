package com.appdirect.pact.provider.service;

import java.util.List;

import com.appdirect.pact.provider.model.ProductDTO;

public interface ProductService {

	List<ProductDTO> getProducts(List<String> ids);

}
