package com.target.casestudy.myRetail.service;

import java.util.List;

import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;

public interface ProductService {

	List<Pricing> getPricingDetails();
	
	void updateProduct(int id, Product product);

	Product getProductFromAPI(int id);

}
