package com.appdirect.pact.provider.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.pact.provider.api.model.ProductWsDTO;
import com.appdirect.pact.provider.facade.ProductFacade;

@RestController
public class DefaultProductsApiController implements ProductsApiController {

	private ProductFacade productFacade;

	@Autowired
	public DefaultProductsApiController(final ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	@RequestMapping(value = "/products/{id}",
			produces = { "application/json" },
			consumes = { "application/json" },
			method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<ProductWsDTO>> getProducts(@PathVariable List<String> id) {
		return ResponseEntity.ok(
				productFacade.getProducts(id).stream().map(productDTO ->
					new ProductWsDTO().id(productDTO.getId()).name(productDTO.getName()).content(productDTO.getContent())
				).collect(Collectors.toList())
		);
	}
}
