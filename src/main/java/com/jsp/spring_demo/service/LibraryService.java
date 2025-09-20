package com.jsp.spring_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_demo.dto.LibraryDTO;
import com.jsp.spring_demo.entity.Library;
import com.jsp.spring_demo.mapper.LibraryMapper;
import com.jsp.spring_demo.repository.LibraryRepository;

@Service
public class LibraryService {
	private final LibraryRepository libraryRepository;
	private final LibraryMapper libraryMapper;
	
	@Autowired
	public LibraryService(LibraryRepository libraryRepository, LibraryMapper libraryMapper) {
		this.libraryRepository = libraryRepository;
		this.libraryMapper = libraryMapper;
	}
	
	public List<LibraryDTO> findAll() {
		List<Library> libraries = libraryRepository.findAll();
		List<LibraryDTO> librariesDTO = new ArrayList<>();
		for(Library library : libraries) {
			librariesDTO.add(libraryMapper.toDTO(library));
		}
		return librariesDTO;
	}
}
