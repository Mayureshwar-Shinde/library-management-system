package com.jsp.spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.spring_demo.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

}
