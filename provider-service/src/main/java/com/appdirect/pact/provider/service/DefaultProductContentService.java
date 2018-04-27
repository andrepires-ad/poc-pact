package com.appdirect.pact.provider.service;

public class DefaultProductContentService implements ProductContentService {
	@Override
	public String getProductContent(String id) {
		return "Content for " + id;
	}
}
