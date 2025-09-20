package com.jsp.spring_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring_demo.dto.LibraryDTO;
import com.jsp.spring_demo.responsestructure.ApiResponse;
import com.jsp.spring_demo.service.LibraryService;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {
	private final LibraryService libraryService;
	
	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<LibraryDTO>>> getAllLibraries() {
		List<LibraryDTO> librariesDTO = libraryService.findAll();
		ApiResponse<List<LibraryDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), librariesDTO,
				"Records fetched successfully.");
		return ResponseEntity.ok(apiResponse);
	}
}
