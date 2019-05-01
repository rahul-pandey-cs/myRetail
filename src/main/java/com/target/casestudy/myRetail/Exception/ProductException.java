package com.target.casestudy.myRetail.Exception;

import org.springframework.http.HttpStatus;

/**
 * @author Rahul Pandey
 *
 */
public class ProductException extends CommonException {

	private static final long serialVersionUID = 3803298523487426652L;

	public ProductException(HttpStatus statusCode, String message) {
		super(statusCode, message);
	}

	public ProductException(HttpStatus statusCode, String message, String details) {
		super(statusCode, message, details);
	}
}
