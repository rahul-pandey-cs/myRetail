package com.target.casestudy.myRetail.beans;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
public class Product {

	String id;

	@Setter(AccessLevel.NONE)
	String name;

	Pricing currentPrice;

	@SuppressWarnings("unchecked")
	@JsonProperty("product")
	private void unpackNestedJson(Map<String, Object> product) {
		Map<String, Object> item = (Map<String, Object>) product.get("item"); 
		this.name = ((Map<String, Object>) item.get("product_description")).get("title").toString();
	}

}
