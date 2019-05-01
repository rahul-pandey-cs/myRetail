package com.target.casestudy.myRetail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.target.casestudy.myRetail.model.Product;

/**
 * @author Rahul Pandey
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Product> {

	Product findById(String id);
}
