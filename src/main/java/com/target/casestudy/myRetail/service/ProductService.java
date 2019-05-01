package com.target.casestudy.myRetail.service;

import java.io.IOException;
import java.util.List;

import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;

public interface ProductService {

	List<Pricing> getPricingDetails() throws IOException;
	
	void updateProduct(int id, Product product);

	Product getProductFromAPI(int id);
	
	public Pricing getSinglePricing(Product product);

}
