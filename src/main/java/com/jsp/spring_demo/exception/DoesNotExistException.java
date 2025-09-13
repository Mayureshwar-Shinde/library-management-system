package com.jsp.spring_demo.exception;

public class DoesNotExistException extends RuntimeException {
	
	public DoesNotExistException(String message) {
		super(message);
	}
}

