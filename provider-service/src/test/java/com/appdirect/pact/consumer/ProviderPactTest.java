package com.appdirect.pact.consumer;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import com.appdirect.pact.provider.Application;
import com.appdirect.pact.provider.api.model.ProductWsDTO;
import com.appdirect.pact.provider.service.products.ProductService;

@RunWith(SpringRestPactRunner.class)
@Provider("provider-service")
@PactBroker(failIfNoPactsFound = false)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderPactTest {

    private static final String UUID = "e2490de5-5bd3-43d5-b7c4-526e33f71304";

    @MockBean
    private ProductService productService;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State("product ids exist")
    public void myServiceReturnsProductsInAscendingOrder() {
        reset(productService);

        when(productService.getProducts(Arrays.asList(UUID, UUID, UUID))).thenReturn(
            Arrays.asList(
                    new ProductWsDTO().id(UUID).name(UUID),
                    new ProductWsDTO().id(UUID).name(UUID),
                    new ProductWsDTO().id(UUID).name(UUID)
            )
        );
    }

}