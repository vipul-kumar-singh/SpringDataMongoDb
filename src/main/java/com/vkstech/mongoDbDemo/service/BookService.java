package com.vkstech.mongoDbDemo.service;

import com.vkstech.mongoDbDemo.constants.ResponseMsg;
import com.vkstech.mongoDbDemo.dto.BookDto;
import com.vkstech.mongoDbDemo.dto.ResponseDto;
import com.vkstech.mongoDbDemo.model.Book;
import com.vkstech.mongoDbDemo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    public ResponseEntity<ResponseDto> saveBook(BookDto bookdto) {
        LOGGER.info("BookService :: saveBook");
        Book book = bookRepository.findById(bookdto.getId()).orElse(null);
        if (Objects.nonNull(book))
            return new ResponseEntity<>(new ResponseDto(ResponseMsg.BOOK_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);

        book = new Book();
        BeanUtils.copyProperties(bookdto, book);
        bookRepository.save(book);
        return new ResponseEntity<>(new ResponseDto(bookdto, ResponseMsg.BOOK_SAVED), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getAllBooks() {
        LOGGER.info("BookService :: getAllBooks");
        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<>(new ResponseDto(books, ResponseMsg.BOOKS_FETCHED), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getBookById(Long id) {
        LOGGER.info("BookService :: getBookById -> {}", id);
        Book book = bookRepository.findById(id).orElse(null);
        if (Objects.isNull(book))
            return new ResponseEntity<>(new ResponseDto(ResponseMsg.BOOK_NOT_EXISTS), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ResponseDto(book, ResponseMsg.BOOK_FETCHED), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> deleteBookById(Long id) {
        LOGGER.info("BookService :: deleteBookById -> {}", id);
        bookRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseDto(ResponseMsg.BOOK_DELETED), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> editBookById(BookDto bookdto) {
        LOGGER.info("BookService :: editBookById -> {}", bookdto.getId());
        Book book = bookRepository.findById(bookdto.getId()).orElse(null);
        if (Objects.isNull(book))
            return new ResponseEntity<>(new ResponseDto(ResponseMsg.BOOK_NOT_EXISTS), HttpStatus.BAD_REQUEST);

        BeanUtils.copyProperties(bookdto, book);
        bookRepository.save(book);
        return new ResponseEntity<>(new ResponseDto(bookdto, ResponseMsg.BOOK_SAVED), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getBookByAuthor(String author) {
        LOGGER.info("BookService :: getBookByAuthor -> {}", author);
        List<Book> books = bookRepository.findByAuthorName(author);
        return new ResponseEntity<>(new ResponseDto(books, ResponseMsg.BOOKS_FETCHED), HttpStatus.OK);
    }
}
