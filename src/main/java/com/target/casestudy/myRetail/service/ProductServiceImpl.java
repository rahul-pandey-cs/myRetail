package com.target.casestudy.myRetail.service;

import static java.lang.Thread.currentThread;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Pricing> getPricingDetails() {

		Charset charset = StandardCharsets.UTF_8;
		final InputStream inputStream = currentThread().getContextClassLoader().getResourceAsStream("pricing.json");

		Pricing pricingJson = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			pricingJson = objectMapper.readValue(IOUtils.toString(inputStream, charset), Pricing.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Pricing> pricingList = new ArrayList<Pricing>();
		pricingList.add(pricingJson);
		return pricingList;
	}

	@Override
	public void updateProduct(int id, Product product) {
		List<Pricing> price;
		price = product.getCurrentPrice();

		if (!price.isEmpty()) {
			price.get(0).setId(String.valueOf(id));
		}
		product.setCurrentPrice(price);
	}

}
