package com.target.casestudy.myRetail;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.target.casestudy.myRetail.model.Pricing;
import com.target.casestudy.myRetail.model.Product;
import com.target.casestudy.myRetail.service.ProductServiceImpl;
import com.target.casestudy.myRetail.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@InjectMocks
	@Autowired
	ProductServiceImpl productService;

	@Mock
	RestTemplate restTemplate;

	@Before
	public void setup() {
		Product pro = new Product();
		pro.setId("123");
		ArrayList<Pricing> priceList = new ArrayList<Pricing>();
		Pricing price1 = new Pricing();
		price1.setCurrencyCode("INR");
		price1.setPriceid(456);
		price1.setValue("400");
		priceList.add(price1);
		pro.setCurrentPrice(priceList);

		ResponseEntity<Product> proEntity1 = new ResponseEntity<Product>(pro, HttpStatus.ACCEPTED);
		when(restTemplate.getForEntity(Constants.TARGET_RESOURCE_URL + "/" + "123" + Constants.PRODUCT_QUERY,
				Product.class)).thenReturn(proEntity1);

		ResponseEntity<Product> proEntity2 = new ResponseEntity<Product>(HttpStatus.ACCEPTED);
		when(restTemplate.getForEntity(Constants.TARGET_RESOURCE_URL + "/" + "451" + Constants.PRODUCT_QUERY,
				Product.class)).thenReturn(proEntity2);

	}

	@Test
	public void testGetPricingDetails() throws IOException {
		Assert.notNull(productService.getPricingDetails());
		Assert.notEmpty(productService.getPricingDetails());
		Assert.isTrue(productService.getPricingDetails().size() == 1);
	}

	@Test
	public void testUpdateProduct() {

	}

	@Test
	public void testGetProductFromAPI() {
		Assert.notNull(productService.getProductFromAPI(123));
		Assert.isNull(productService.getProductFromAPI(451));
	}

}
