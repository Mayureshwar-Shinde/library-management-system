package com.jsp.spring_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_demo.dto.BookDTO;
import com.jsp.spring_demo.entity.Book;
import com.jsp.spring_demo.repository.BookRepository;


@Service
public class BookService {
	private final BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<BookDTO> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookDTO> bookDTOs = new ArrayList<>();
		for(Book book : books) {
			bookDTOs.add(mapToDTO(book));
		}
		return bookDTOs;
	}
	
	
	public BookDTO mapToDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setName(book.getName());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setDescription(book.getDescription());
		bookDTO.setLibraryId(book.getLibrary() == null ? null : book.getLibrary().getId());
		
		return bookDTO;
	}
}

