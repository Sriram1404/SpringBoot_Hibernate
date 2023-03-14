package com.example.jpa.hibernatedemo.dao;

import com.example.jpa.hibernatedemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookDao extends JpaRepository<Book, Long> {

    List<Book> findByAuthorName(String authorName);
}
