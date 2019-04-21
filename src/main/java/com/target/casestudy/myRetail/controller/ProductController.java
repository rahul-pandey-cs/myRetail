package com.target.casestudy.myRetail.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myRetail.beans.Product;

@RestController
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@RequestMapping(value = "products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getGallery(@PathVariable final int id) {
		final String QUERY = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics,available_to_promise_network,deep_red_labels";
		final String targetResourceUrl = "https://redsky.target.com/v2/pdp/tcin";
		
		ResponseEntity<Product> resultVal = restTemplate.getForEntity(targetResourceUrl + "/" + id + QUERY, Product.class);
		
//		ResponseEntity<Map> resultVal = restTemplate.getForEntity(targetResourceUrl + "/" + id + QUERY, Map.class);
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.readValue(resultVal.getBody().get("product"), Product.class);
		return resultVal;
	}

}
