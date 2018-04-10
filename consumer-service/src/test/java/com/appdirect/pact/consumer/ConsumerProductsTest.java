package com.appdirect.pact.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.appdirect.pact.consumer.apiclients.ProductWsDTO;
import com.appdirect.pact.consumer.apiclients.ProductsApiImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import okhttp3.Request;
import retrofit2.Call;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ConsumerProductsTest {

	@Rule
    public PactProviderRuleMk2 productsRule = new PactProviderRuleMk2("provider-service", "localhost", 8080, this);

	@NotNull
	private List<ProductWsDTO> getProductWsDTOS() {
		return Arrays.asList(
				ProductWsDTO.newBuilder().id("id-1").name("name-1").build(),
				ProductWsDTO.newBuilder().id("id-2").name("name-2").build(),
				ProductWsDTO.newBuilder().id("id-3").name("name-3").build()
		);
	}

	@Pact(consumer="consumer-service", provider="provider-service")
	public RequestResponsePact createFragments(PactDslWithProvider builder) throws JsonProcessingException {

		final Map<String, String> headers = ImmutableMap.<String, String>builder()
				.put("Content-Type", "application/json")
				.build();

		return builder
				//
				.given("products in ascending order")
				.uponReceiving("request for products in ascending order")
				.path("/products")
				.method("GET")
				.headers(headers)
				.willRespondWith()
				.status(200)
				.body(new ObjectMapper().writeValueAsString(
						getProductWsDTOS().stream().sorted(comparing(ProductWsDTO::getId)).collect(toList())
				))
				.headers(headers)

				.uponReceiving("request for products in descending order")
				.path("/products")
				.method("GET")
				.headers(headers)
				.willRespondWith()
				.status(200)
				.body(new ObjectMapper().writeValueAsString(
						getProductWsDTOS().stream().sorted(comparing(ProductWsDTO::getId).reversed()).collect(toList())
				))
				.headers(headers)
				.toPact();
	}

	@Test
    @PactVerification("provider-service")
    public void runTest() throws IOException {
		final ProductsApiImpl productsApi = new ProductsApiImpl();
		productsApi.setBaseUrl(productsRule.getConfig().url());

		List<ProductWsDTO> list = productsApi.getProducts().execute().body();

		assertTrue(list.size() > 0);
		assertEquals(list.size(), getProductWsDTOS().size());
		assertEquals(list.get(0).getId(), getProductWsDTOS().get(0).getId());
    }
}
