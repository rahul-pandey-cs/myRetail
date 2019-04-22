package com.target.casestudy.myRetail.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@Entity
@Table(name = "Product")
@Access(value=AccessType.FIELD)
public class Product implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 2050137481113252459L;

	@Id
	String id;
   
    @Column(name = "Name")
	@Setter(AccessLevel.NONE)
	String name;

    @Column(name = "CurrentPrice")
    @OneToMany(mappedBy="id", fetch = FetchType.LAZY, targetEntity=Pricing.class)
	List<Pricing> currentPrice;

	@SuppressWarnings("unchecked")
	@JsonProperty("product")
	public void unpackNestedJson(Map<String, Object> product) {

		Map<String, Object> item = (Map<String, Object>) product.get("item");
		this.name = ((Map<String, Object>) item.get("product_description")).get("title").toString();
		this.id = item.get("tcin").toString();
	}

}
