package com.appdirect.pact.consumer.apiclients;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class ProductsApiImpl implements ProductsApi {

	@Value("${provider.url}")
	private String baseUrl;

	@Override
	public Call<List<ProductWsDTO>> getProducts(String ids) {


		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

		OkHttpClient client = builder.build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(getBaseUrl())
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		ProductsApi productsAPI = retrofit.create(ProductsApi.class);

		return productsAPI.getProducts(ids);
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
