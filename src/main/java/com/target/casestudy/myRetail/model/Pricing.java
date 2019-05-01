package com.target.casestudy.myRetail.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author Rahul Pandey
 *
 */
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
	
	@Column(name = "ProductID")
	@JsonIgnore
	String productId;
	
	@Column(name = "CurrencyCode")
	String currencyCode;

	@Column(name = "Value")
	String value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductID", referencedColumnName = "Id", nullable = false, updatable = false, insertable = false)
	@JsonIgnore
	Product productpricing;

	
}
