package com.appdirect.pact.consumer;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

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
import com.appdirect.pact.provider.facade.ProductFacade;
import com.appdirect.pact.provider.model.ProductDTO;

@RunWith(SpringRestPactRunner.class)
@Provider("provider-service")
@PactBroker(failIfNoPactsFound = false)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderPactTest {

    private static final String UUID = "e2490de5-5bd3-43d5-b7c4-526e33f71304";

    @MockBean
    private ProductFacade productFacade;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State("product ids exist")
    public void myServiceReturnsProductsInAscendingOrder() {
        reset(productFacade);

        when(productFacade.getProducts(asList(UUID, UUID, UUID))).thenReturn(
            asList(
                    new ProductDTO().id(UUID).name(UUID).content(UUID),
                    new ProductDTO().id(UUID).name(UUID).content(UUID),
                    new ProductDTO().id(UUID).name(UUID).content(UUID)
            )
        );

    }

}