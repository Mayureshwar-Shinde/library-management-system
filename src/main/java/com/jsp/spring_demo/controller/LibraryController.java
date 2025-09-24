package com.jsp.spring_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
				"Libraries fetched successfully.");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<LibraryDTO>> getLibrary(@PathVariable int id) {
		ApiResponse<LibraryDTO> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), libraryService.find(id),
				"Library fetched successfully.");
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<LibraryDTO>> createLibrary(@RequestBody LibraryDTO libraryDTO) {
		LibraryDTO createdLibraryDTO = libraryService.createLibrary(libraryDTO);
		ApiResponse<LibraryDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), createdLibraryDTO,
				"Library created successfully.");
		return new ResponseEntity<ApiResponse<LibraryDTO>> (apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<LibraryDTO>> updateLibrary(@RequestBody LibraryDTO libraryDTO, @PathVariable int id) {
		LibraryDTO updatedLibraryDTO = libraryService.updateLibrary(libraryDTO, id);
		ApiResponse<LibraryDTO> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), updatedLibraryDTO,
				"Library updated successfully.");
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLibrary(@PathVariable int id) {
		libraryService.deleteLibrary(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
