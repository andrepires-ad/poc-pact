package com.appdirect.pact.provider.facade;


import java.util.List;

import org.springframework.stereotype.Service;

import com.appdirect.pact.provider.model.ProductDTO;

@Service
public interface ProductFacade {

	List<ProductDTO> getProducts(List<String> ids);
}
