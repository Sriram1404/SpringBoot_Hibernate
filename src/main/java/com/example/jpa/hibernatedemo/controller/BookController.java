package com.example.jpa.hibernatedemo.controller;

import com.example.jpa.hibernatedemo.dao.BookDao;
import com.example.jpa.hibernatedemo.dto.BookDto;
import com.example.jpa.hibernatedemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;


    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBook(@RequestBody @Valid BookDto bookDto) {
        Book book = new Book(bookDto);
        bookDao.save(book);
        return ResponseEntity.ok("Successfully saved the book");
    }

    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBookById(@PathVariable("id") Long id) {
        Book book = bookDao.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.status(404).body("Book not found");
        }
        BookDto bookDto = new BookDto(book);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAllBooks(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageSize) {
        List<BookDto> books = bookDao.findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(book -> new BookDto(book)).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{authorName}")
    public ResponseEntity findByAuthorName(@PathVariable String authorName) {
        List<BookDto> books = bookDao.findByAuthorName(authorName)
                .stream()
                .map(book -> new BookDto(book)).collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/books/totalCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTotalBooksCount() {
        return ResponseEntity.ok(bookDao.count());
    }
}
