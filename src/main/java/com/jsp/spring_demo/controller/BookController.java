package com.jsp.spring_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring_demo.dto.BookDTO;
import com.jsp.spring_demo.responsestructure.ApiResponse;
import com.jsp.spring_demo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<BookDTO>>> getAllBooks() {
		ApiResponse<List<BookDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), bookService.findAll(),
				"Books fetched successfully.");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BookDTO>> getBook(@PathVariable int id) {
		return ResponseEntity.ok(new ApiResponse<BookDTO>(HttpStatus.OK.value(), bookService.find(id),
				"Book fetched successfully."));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<BookDTO>> createBook(@RequestBody BookDTO bookDTO) {
		ApiResponse<BookDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), bookService.createBook(bookDTO),
				"Book created successfully.");
		return new ResponseEntity<ApiResponse<BookDTO>>(apiResponse, HttpStatus.CREATED);
	}
}

