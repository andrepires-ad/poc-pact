package com.appdirect.pact.provider.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.appdirect.pact.provider.api.model.ProductWsDTO;

public interface ProductsApiController {

	ResponseEntity<List<ProductWsDTO>> getProducts(@PathVariable List<String> id);
}
