package com.appdirect.pact.consumer;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ConsumerPortTest {

    @Rule
    public PactProviderRule rule = new PactProviderRule("Product_Provider", this);

    @Pact(provider="Product_Provider", consumer="Product_Consumer")
    public PactFragment createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder
                .uponReceiving("a request for Products")
                .path("/products")
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("[{\"value\":2}, {\"value\":3}]").toFragment();
    }

    @Test
    @PactVerification("Product_Provider")
    public void runTest() {
        assertEquals(new ConsumerPort(rule.getConfig().url()).products(), Arrays.asList(new Product(2), new Product(3)));
    }
}
