package com.appdirect.pact.consumer.apiclients;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class ProductsApiImpl implements ProductsApi {

	@Value("${provider.url}")
	private String baseUrl;

	@Override
	public Call<List<ProductWsDTO>> getProducts() {

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(getBaseUrl())
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		ProductsApi productsAPI = retrofit.create(ProductsApi.class);

		return productsAPI.getProducts();
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
