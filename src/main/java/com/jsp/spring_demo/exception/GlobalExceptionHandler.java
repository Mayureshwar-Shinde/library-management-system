package com.jsp.spring_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.spring_demo.responsestructure.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleDoesNotExistException(DoesNotExistException doesNotExistException) {
		ErrorResponse errorResponse = new ErrorResponse(doesNotExistException.getMessage());
		return new ResponseEntity<ErrorResponse> (errorResponse, HttpStatus.NOT_FOUND);
	}
}

