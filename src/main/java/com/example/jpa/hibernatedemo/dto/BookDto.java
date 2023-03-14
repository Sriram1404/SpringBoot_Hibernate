package com.example.jpa.hibernatedemo.dto;

import com.example.jpa.hibernatedemo.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    @NotNull(message = "Book name cannot be null")
    private String name;
    @NotNull(message = "Author name cannot be null")
    private String authorName;
    @NotNull(message = "Book cost cannot be null")
    private int cost;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authorName = book.getAuthorName();
        this.cost = book.getCost();
    }
}
