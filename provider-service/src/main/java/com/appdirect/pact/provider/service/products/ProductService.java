package com.appdirect.pact.provider.service.products;

import java.util.List;

import com.appdirect.pact.provider.api.model.ProductWsDTO;

public interface ProductService {

	List<ProductWsDTO> getProducts(List<String> ids);

}
