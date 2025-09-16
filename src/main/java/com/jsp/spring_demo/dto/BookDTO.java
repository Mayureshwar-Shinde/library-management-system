package com.jsp.spring_demo.dto;

import lombok.Data;

@Data
public class BookDTO {
	private int id;
	private String name;
	private String author;
	private String description;
	private Integer libraryId;
}

