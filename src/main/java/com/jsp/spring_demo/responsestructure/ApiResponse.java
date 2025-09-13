package com.jsp.spring_demo.responsestructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
	private int code;
	private T data;
	private String message;
}

