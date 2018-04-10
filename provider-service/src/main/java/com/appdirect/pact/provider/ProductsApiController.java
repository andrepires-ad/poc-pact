package com.appdirect.pact.provider;

import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.provider.api.ProductsApi;
import com.appdirect.provider.model.ProductWsDTO;

@RestController
public class ProductsApiController implements ProductsApi {

	private static final String PACT_REQUEST = "Pact-Request";
	@Autowired
	private HttpServletRequest request;

	@Override
	public ResponseEntity<List<ProductWsDTO>> getProducts() {
		final String pactRequest = getRequest().getHeader(PACT_REQUEST);
		if (pactRequest != null) {

			return ResponseEntity.status(OK)
					.header(PACT_REQUEST, PACT_REQUEST)
					.body(
						Arrays.asList(
						new ProductWsDTO().id("id-1").name("name-1"),
						new ProductWsDTO().id("id-2").name("name-2"),
						new ProductWsDTO().id("id-3").name("name-3")
					));

		}
		return ResponseEntity.ok(
				Arrays.asList(
						new ProductWsDTO().id("1").name("Sunscreen"),
						new ProductWsDTO().id("2").name("Summer chair")
				)
		);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
