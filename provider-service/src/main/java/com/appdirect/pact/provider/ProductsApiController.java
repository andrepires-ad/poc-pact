package com.appdirect.pact.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.pact.provider.service.products.ProductService;
import com.appdirect.provider.api.ProductsApi;
import com.appdirect.provider.model.ProductWsDTO;

@RestController
public class ProductsApiController implements ProductsApi {

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<List<ProductWsDTO>> getProducts() {
		return ResponseEntity.ok(
			getProductService().getProducts()
		);
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
