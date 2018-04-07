package com.appdirect.pact.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ConsumerPortTest {

    @Rule
    public PactProviderRuleMk2 rule = new PactProviderRuleMk2("Product_Provider", this);

    @Pact(provider="Product_Provider", consumer="Product_Consumer")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("pact-request-test", "TEST");

        return builder
                .given("test state")
				.uponReceiving("a first request for /products")
                .path("/products")
                .method("GET")
                .headers(headers)
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("[{\"id\":\"1\", \"name\":\"1\"}, {\"id\":\"1\", \"name\":\"1\"}]")
                .toPact();

    }

    //@Test
    @PactVerification("Product_Provider")
    public void runTest() {
        assertEquals(new ConsumerPort(rule.getConfig().url()).products(), Arrays.asList(new Product(2), new Product(3)));
    }
}
