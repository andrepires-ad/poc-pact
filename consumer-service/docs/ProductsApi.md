# ProductsApi

All URIs are relative to *https://virtserver.swaggerhub.com/team-support/provider-service/0.1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getProducts**](ProductsApi.md#getProducts) | **GET** products | Get product list


<a name="getProducts"></a>
# **getProducts**
> List&lt;ProductWsDTO&gt; getProducts()

Get product list

Returns a product list.

### Example
```java
// Import classes:
//import com.appdirect.provider.ApiException;
//import ProductsApi;


ProductsApi apiInstance = new ProductsApi();
try {
    List<ProductWsDTO> result = apiInstance.getProducts();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#getProducts");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ProductWsDTO&gt;**](ProductWsDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

