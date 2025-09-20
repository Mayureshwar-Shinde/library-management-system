package com.jsp.spring_demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jsp.spring_demo.dto.BookDTO;
import com.jsp.spring_demo.dto.LibraryDTO;
import com.jsp.spring_demo.entity.Book;
import com.jsp.spring_demo.entity.Library;

@Component
public class LibraryMapper {
	private final BookMapper bookMapper;
	
	public LibraryMapper(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	public LibraryDTO toDTO(Library library) {
		LibraryDTO libraryDTO = new LibraryDTO();
		libraryDTO.setId(library.getId());
		libraryDTO.setName(library.getName());
		libraryDTO.setLocation(library.getLocation());
		
		Set<Book> books = library.getBooks();
		List<BookDTO> booksDTO = new ArrayList<>();
		for(Book book : books) {
			booksDTO.add(bookMapper.toDTO(book));
		}
		libraryDTO.setBooks(booksDTO);
		
		return libraryDTO;
	}
}
