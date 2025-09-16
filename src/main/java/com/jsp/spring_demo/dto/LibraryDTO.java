package com.jsp.spring_demo.dto;

import java.util.List;

import lombok.Data;


@Data
public class LibraryDTO {
	private int id;
	private String name;
	private String location;
	private List<BookDTO> books;
}

