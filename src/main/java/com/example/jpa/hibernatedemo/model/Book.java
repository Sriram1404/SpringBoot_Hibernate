package com.example.jpa.hibernatedemo.model;

import com.example.jpa.hibernatedemo.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private Long id = System.currentTimeMillis();
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "author_name", nullable = false)
    private String authorName;
    @Column(name = "cost", nullable = false)
    private int cost;


    public Book(BookDto bookDto) {
        this.name = bookDto.getName();
        this.authorName = bookDto.getAuthorName();
        this.cost = bookDto.getCost();
    }
}
