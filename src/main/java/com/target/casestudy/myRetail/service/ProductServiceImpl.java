package com.target.casestudy.myRetail.service;

import static java.lang.Thread.currentThread;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myRetail.Exception.ProductException;
import com.target.casestudy.myRetail.controller.ProductController;
import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;
import com.target.casestudy.myRetail.util.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Pricing> getPricingDetails() {

		Charset charset = StandardCharsets.UTF_8;
		final InputStream inputStream = currentThread().getContextClassLoader().getResourceAsStream("pricing.json");

		Pricing pricingJson = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			pricingJson = objectMapper.readValue(IOUtils.toString(inputStream, charset), Pricing.class);
		} catch (IOException e) {
			logger.error("IOException occured at getPricingDetails");
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

	@Override
	public Product getProductFromAPI(int id) {
		
		Product resultVal = new Product();

		try {
			resultVal = restTemplate
					.getForEntity(Constants.TARGET_RESOURCE_URL + "/" + id + Constants.PRODUCT_QUERY, Product.class)
					.getBody();

		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				logger.error("Product resource with id: " + id + " not found !!");
				throw new ProductException(e.getStatusCode(), Constants.PRODUCT_DOES_NOT_EXIST,
						e.getLocalizedMessage());
			} else {
				logger.error("Exception occured while calling ProductAPI");
				throw new ProductException(e.getStatusCode(), e.getMessage());
			}
		}
		return resultVal;
	}

}
