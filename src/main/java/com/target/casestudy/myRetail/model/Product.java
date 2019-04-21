package com.target.casestudy.myRetail.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column
	String id;
   
    @Column(name = "Name")
	@Setter(AccessLevel.NONE)
	String name;

    @Column(name = "CurrentPrice")
	Pricing currentPrice;

	@SuppressWarnings("unchecked")
	@JsonProperty("product")
	private void unpackNestedJson(Map<String, Object> product) {

		Map<String, Object> item = (Map<String, Object>) product.get("item");
		this.name = ((Map<String, Object>) item.get("product_description")).get("title").toString();
		this.id = item.get("tcin").toString();
	}

}
