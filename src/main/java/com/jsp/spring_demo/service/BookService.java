package com.jsp.spring_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_demo.dto.BookDTO;
import com.jsp.spring_demo.entity.Book;
import com.jsp.spring_demo.entity.Library;
import com.jsp.spring_demo.exception.DoesNotExistException;
import com.jsp.spring_demo.mapper.BookMapper;
import com.jsp.spring_demo.repository.BookRepository;
import com.jsp.spring_demo.repository.LibraryRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	private final LibraryRepository libraryRepository;
	private final BookMapper bookMapper;

	@Autowired
	public BookService(BookRepository bookRepository,
			LibraryRepository libraryRepository,
			BookMapper bookMapper) {
		this.bookRepository = bookRepository;
		this.libraryRepository = libraryRepository;
		this.bookMapper = bookMapper;
	}

	public List<BookDTO> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookDTO> bookDTOs = new ArrayList<>();
		for (Book book : books) {
			bookDTOs.add(bookMapper.toDTO(book));
		}
		return bookDTOs;
	}

	public BookDTO find(int id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new DoesNotExistException("Book with ID: " + id + " does not exist."));
		return bookMapper.toDTO(book);
	}

	public BookDTO createBook(BookDTO bookDTO) {
		Integer libraryId = bookDTO.getLibraryId();
		Library library = libraryId == null ? null
				: libraryRepository.findById(libraryId).orElseThrow(
						() -> new DoesNotExistException("Library with ID: " + libraryId + " does not exist."));
		Book book = bookRepository.save(bookMapper.toEntity(bookDTO, library));
		return bookMapper.toDTO(book);
	}
	
	public BookDTO updateBook(BookDTO bookDTO, int id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new DoesNotExistException("Book with ID: " + id + " does not exist."));
		Integer libraryId = bookDTO.getLibraryId();
		Library library = libraryId == null ? null
				: libraryRepository.findById(libraryId).orElseThrow(
						() -> new DoesNotExistException("Library with ID: " + libraryId + " does not exist."));
		Book savedBook = bookRepository.save(bookMapper.updateEntity(book, bookDTO, library));
		return bookMapper.toDTO(savedBook);
	}
	
	public void deleteBook(int id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new DoesNotExistException("Book with ID: " + id + " does not exist."));
		bookRepository.delete(book);
	}
}
