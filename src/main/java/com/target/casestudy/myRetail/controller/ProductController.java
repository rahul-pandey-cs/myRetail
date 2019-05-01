package com.target.casestudy.myRetail.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;
import com.target.casestudy.myRetail.repository.PriceRepository;
import com.target.casestudy.myRetail.repository.ProductRepository;
import com.target.casestudy.myRetail.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Rahul Pandey
 *
 */
@Api(tags = { "Product" })
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
	ProductRepository repository;

	@Autowired
	PriceRepository priceRepository;

	@ApiOperation(value = "Get the product details for the supplied product id")
	@GetMapping(ProductController.API_PRODUCT + "{id}")
	public ResponseEntity<Product> getProduct(@PathVariable final int id) throws IOException {

		Product product = productService.getProductFromAPI(id);
		List<Pricing> productPrice = productService.getPricingDetails();
		product.setCurrentPrice(productPrice);
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}

	@PostMapping(ProductController.API_PRODUCT + "{id}")
	public ResponseEntity<Product> createProduct(@PathVariable final int id, @RequestBody Product product) {
		productService.updateProduct(id, product);
		repository.save(product);
		logger.info("Product created successfully");
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}

	@GetMapping(ProductController.API_PRODUCT_DATASOURCE + "{id}")
	public ResponseEntity<?> getProductFromDataSource(@PathVariable final int id) {
		Product productValue = repository.findById(String.valueOf(id));
		return new ResponseEntity<>(productValue, HttpStatus.ACCEPTED);
	}

}
