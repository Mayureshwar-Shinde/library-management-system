package com.jsp.spring_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_demo.dto.BookDTO;
import com.jsp.spring_demo.entity.Book;
import com.jsp.spring_demo.exception.DoesNotExistException;
import com.jsp.spring_demo.repository.BookRepository;
import com.jsp.spring_demo.repository.LibraryRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	private final LibraryRepository libraryRepository;

	@Autowired
	public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
		this.bookRepository = bookRepository;
		this.libraryRepository = libraryRepository;
	}

	public List<BookDTO> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookDTO> bookDTOs = new ArrayList<>();
		for (Book book : books) {
			bookDTOs.add(mapToDTO(book));
		}
		return bookDTOs;
	}

	public BookDTO find(int id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new DoesNotExistException("Book with ID: " + id + " does not exist."));
		return mapToDTO(book);
	}

	public BookDTO createBook(BookDTO bookDTO) {
		return mapToDTO(bookRepository.save(mapToEntity(new Book(), bookDTO)));
	}
	
	public BookDTO updateBook(BookDTO bookDTO, int id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new DoesNotExistException("Book with ID: " + id + " does not exist."));
		return mapToDTO(bookRepository.save(mapToEntity(book, bookDTO)));
	}
	
	public void deleteBook(int id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new DoesNotExistException("Book with ID: " + id + " does not exist."));
		bookRepository.delete(book);
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

	public Book mapToEntity(Book book, BookDTO bookDTO) {
		book.setName(bookDTO.getName());
		book.setAuthor(bookDTO.getAuthor());
		book.setDescription(bookDTO.getDescription());
		Integer libraryId = bookDTO.getLibraryId();
		book.setLibrary(libraryId == null ? null
				: libraryRepository.findById(libraryId).orElseThrow(
						() -> new DoesNotExistException("Library with ID: " + libraryId + " does not exist.")));
		return book;
	}
}
