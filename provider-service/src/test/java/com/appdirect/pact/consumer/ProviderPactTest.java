package com.appdirect.pact.consumer;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import com.appdirect.pact.provider.Application;
import com.appdirect.pact.provider.ProductsApiController;
import com.appdirect.provider.model.ProductWsDTO;

@RunWith(SpringRestPactRunner.class)
@Provider("provider-service")
@PactBroker(failIfNoPactsFound = false, tags="master")
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderPactTest {

    @MockBean
    private ProductsApiController productsApiController;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State("products in ascending order")
    public void myServiceReturnsProductsInAscendingOrder() {
        reset(productsApiController);

        when(productsApiController.getProducts()).thenReturn(ResponseEntity.ok(
                Arrays.asList(
                        new ProductWsDTO().id("id-1").name("name-1"),
                        new ProductWsDTO().id("id-2").name("name-2"),
                        new ProductWsDTO().id("id-3").name("name-3"),
                        new ProductWsDTO().id("id-6").name("name-6")
                )
        ));
    }
}