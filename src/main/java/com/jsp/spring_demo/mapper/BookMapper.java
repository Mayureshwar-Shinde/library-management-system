package com.jsp.spring_demo.mapper;


import org.springframework.stereotype.Component;

import com.jsp.spring_demo.dto.BookDTO;
import com.jsp.spring_demo.entity.Book;
import com.jsp.spring_demo.entity.Library;

@Component
public class BookMapper {
	
	public BookDTO toDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setName(book.getName());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setDescription(book.getDescription());
		Library library = book.getLibrary();
		bookDTO.setLibraryId(library == null ? null : library.getId());
		return bookDTO;
	}
	
	public Book toEntity(BookDTO bookDTO, Library library) {
		Book book = new Book();
		updateEntity(book, bookDTO, library);
		return book;
	}
	
	public Book updateEntity(Book book, BookDTO bookDTO, Library library) {
		book.setName(bookDTO.getName());
		book.setAuthor(bookDTO.getAuthor());
		book.setDescription(bookDTO.getDescription());
		book.setLibrary(library);
		return book;
	}
}

