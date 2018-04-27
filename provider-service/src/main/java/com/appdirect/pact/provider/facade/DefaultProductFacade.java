package com.appdirect.pact.provider.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.appdirect.pact.provider.model.ProductDTO;
import com.appdirect.pact.provider.service.ProductContentService;
import com.appdirect.pact.provider.service.ProductService;

public class DefaultProductFacade implements  ProductFacade {

	private ProductService productService;
	private ProductContentService productContentService;

	@Autowired
	public DefaultProductFacade(final ProductService productService, final ProductContentService productContentService) {
		this.productService = productService;
		this.productContentService = productContentService;
	}

	@Override
	public List<ProductDTO> getProducts(List<String> ids) {
		List<ProductDTO> products = productService.getProducts(ids);
		products.stream().forEach(product ->
			product.content(productContentService.getProductContent(product.getId()))
		);
		return products;
	}
}
