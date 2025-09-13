package com.jsp.spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.spring_demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}

