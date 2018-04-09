package com.appdirect.pact.consumer.model;

import com.appdirect.pact.consumer.apiclients.ProductWsDTO;

public class CartWsDTO {
	private ProductWsDTO product;
	private Integer quantity;

	public CartWsDTO(ProductWsDTO product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public ProductWsDTO getProduct() {
		return product;
	}

	public void setProduct(ProductWsDTO product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
