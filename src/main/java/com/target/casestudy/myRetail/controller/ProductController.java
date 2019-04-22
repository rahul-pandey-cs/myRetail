package com.target.casestudy.myRetail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.myRetail.Repository.ProductRepository;
import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;
import com.target.casestudy.myRetail.service.ProductService;
import com.target.casestudy.myRetail.util.Constants;

@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	protected static final String API_PRODUCT = "products/";
	protected static final String API_PRODUCT_DATASOURCE = "productsDataSource/";

	private final ProductService productService;

	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ProductRepository repository;

	@Autowired
	private Environment env;

	@GetMapping(ProductController.API_PRODUCT + "{id}")
	public ResponseEntity<Product> getProduct(@PathVariable final int id) {
		ResponseEntity<Product> resultVal = restTemplate
				.getForEntity(Constants.TARGET_RESOURCE_URL + "/" + id + Constants.PRODUCT_QUERY, Product.class);
		List<Pricing> productPrice = productService.getPricingDetails();
		resultVal.getBody().setCurrentPrice(productPrice);
		return resultVal;
	}

	@PostMapping(ProductController.API_PRODUCT + "{id}")
	public ResponseEntity<Product> createProduct(@PathVariable final int id, @RequestBody Product product) {
		productService.updateProduct(id,product);
		repository.save(product);
		return new ResponseEntity<Product>(HttpStatus.CREATED);
	}

	@GetMapping(ProductController.API_PRODUCT_DATASOURCE + "{id}")
	public ResponseEntity<Product> getProductFromDataSource(@PathVariable final int id, @RequestBody Product product) {
		Product productValue = repository.findById(String.valueOf(id));
		return new ResponseEntity<Product>(productValue, HttpStatus.ACCEPTED);
	}

}
