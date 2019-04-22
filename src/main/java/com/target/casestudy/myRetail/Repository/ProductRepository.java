package com.target.casestudy.myRetail.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.target.casestudy.myRetail.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Product> {

	Product findById(String id);
}
