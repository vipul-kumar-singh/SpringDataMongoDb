package com.vkstech.mongoDbDemo.controller;

import com.vkstech.mongoDbDemo.dto.BookDto;
import com.vkstech.mongoDbDemo.dto.ResponseDto;
import com.vkstech.mongoDbDemo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping("add")
    public ResponseEntity<ResponseDto> saveBook(@RequestBody BookDto bookDto) {
        LOGGER.info("BookController :: saveBook");
        return bookService.saveBook(bookDto);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseDto> getAllBooks() {
        LOGGER.info("BookController :: getAllBooks");
        return bookService.getAllBooks();
    }

    @GetMapping("find/{id}")
    public ResponseEntity<ResponseDto> getBookById(@PathVariable Long id) {
        LOGGER.info("BookController :: getBookById -> {}", id);
        return bookService.getBookById(id);
    }

    @PutMapping("edit")
    public ResponseEntity<ResponseDto> editBook(@RequestBody BookDto bookDto) {
        LOGGER.info("BookController :: editBook");
        return bookService.editBookById(bookDto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDto> deleteBookById(@PathVariable Long id) {
        LOGGER.info("BookController :: deleteBookById -> {}", id);
        return bookService.deleteBookById(id);
    }

    @GetMapping("find")
    public ResponseEntity<ResponseDto> getBookByAuthor(@RequestParam String author) {
        LOGGER.info("BookController :: getBookByAuthor -> {}", author);
        return bookService.getBookByAuthor(author);
    }
}
