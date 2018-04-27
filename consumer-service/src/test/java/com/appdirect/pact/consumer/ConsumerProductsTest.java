package com.appdirect.pact.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.appdirect.pact.consumer.apiclients.ProductWsDTO;
import com.appdirect.pact.consumer.apiclients.ProductsApiImpl;
import com.google.common.collect.ImmutableMap;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ConsumerProductsTest {

	private static final String UUID = "e2490de5-5bd3-43d5-b7c4-526e33f71304";
	@Rule
    public PactProviderRuleMk2 productsRule = new PactProviderRuleMk2("provider-service", "localhost", 8080, this);

	private PactDslJsonArray buildPactResponse() {
		PactDslJsonArray array = new PactDslJsonArray();

		array
			.object()
				.uuid("id", UUID)
				.stringType("name", UUID)
			.closeObject()
			.object()
				.uuid("id", UUID)
				.stringType("name", UUID)
			.closeObject()
			.object()
				.uuid("id", UUID)
				.stringType("name", UUID)
			.closeObject()
		.closeArray();

		return array;
	}

	@Pact(consumer="consumer-service", provider="provider-service")
	public RequestResponsePact createFragments(PactDslWithProvider builder) {

		final Map<String, String> headers = ImmutableMap.<String, String>builder()
				.put("Content-Type", "application/json")
				.build();

		return builder
				//
				.given("product ids exist")
				.uponReceiving("a request for 3 products")
				.path("/products/" + getProductIds())
				.method("GET")
				.headers(headers)
				.willRespondWith()
				.status(200)
				.body(buildPactResponse())
				.headers(headers)
				.toPact();
	}

	private String getProductIds() {
		return asList(UUID, UUID, UUID).stream().collect(joining(","));
	}

	@Test
    @PactVerification("provider-service")
    public void runTest() throws IOException {
		final ProductsApiImpl productsApi = new ProductsApiImpl();
		productsApi.setBaseUrl(productsRule.getConfig().url());

		List<ProductWsDTO> list = productsApi.getProducts(getProductIds()).execute().body();

		assertNotNull(list);
		assertEquals(3, list.size());
		assertEquals(UUID, list.get(0).getId());
		assertEquals(UUID, list.get(1).getId());
		assertEquals(UUID, list.get(2).getId());
    }
}
