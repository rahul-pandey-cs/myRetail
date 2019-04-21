package com.target.casestudy.myRetail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;
import com.target.casestudy.myRetail.service.ProductService;
import com.target.casestudy.myRetail.util.Constants;

@RestController
public class ProductController {
    
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	protected static final String API_PRODUCT = "products/";
	
	private final ProductService productService;

	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@GetMapping(ProductController.API_PRODUCT + "{id}")
	public ResponseEntity<Product> getGallery(@PathVariable final int id) {
		ResponseEntity<Product> resultVal = restTemplate
				.getForEntity(Constants.TARGET_RESOURCE_URL + "/" + id + Constants.PRODUCT_QUERY, Product.class);
		Pricing productPrice = productService.getPricingDetails();
		resultVal.getBody().setCurrentPrice(productPrice);
		return resultVal;
	}

//	private Pricing getPricingDetails() {
//		Charset charset = StandardCharsets.UTF_8;
//		final InputStream inputStream = currentThread().getContextClassLoader().getResourceAsStream("pricing.json");
//		Pricing pricingJson = null;
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			pricingJson = objectMapper.readValue(IOUtils.toString(inputStream, charset), Pricing.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return pricingJson;
//	}
}
