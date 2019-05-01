package com.target.casestudy.myRetail.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * @author Rahul Pandey
 *
 */
public class CommonException extends HttpStatusCodeException {

	private static final long serialVersionUID = 1687971530824341758L;
	private String details;

	protected CommonException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	protected CommonException(HttpStatus statusCode, String statusText, String details) {
		super(statusCode, statusText);
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
