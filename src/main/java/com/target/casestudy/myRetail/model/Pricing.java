package com.target.casestudy.myRetail.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Pricing")
@Access(value=AccessType.FIELD)
@Embeddable
public class Pricing implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1896465004427322445L;

	@Id
	@Column(name = "PriceId")
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@JsonIgnore
	int priceid;
	
	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Product.class) 
	@JoinColumn(name = "Id")
	@JsonIgnore
	String productId;
	
	@Column(name = "CurrencyCode")
	String currencyCode;

	@Column(name = "Value")
	String value;
	
//	@Override
//    public String toString() {
//        return "pricing(currencyCode=" + currencyCode + ", value=" + value + ")";
//    }
	
}
