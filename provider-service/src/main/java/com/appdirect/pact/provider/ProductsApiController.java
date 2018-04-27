package com.appdirect.pact.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.pact.provider.api.model.ProductWsDTO;
import com.appdirect.pact.provider.service.products.ProductService;

@RestController
public class ProductsApiController {

	@Autowired
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/products/{id}",
			produces = { "application/json" },
			consumes = { "application/json" },
			method = RequestMethod.GET)
	public ResponseEntity<List<ProductWsDTO>> getProducts(@PathVariable List<String> id) {
		return ResponseEntity.ok(
				getProductService().getProducts(id)
		);
	}
}
