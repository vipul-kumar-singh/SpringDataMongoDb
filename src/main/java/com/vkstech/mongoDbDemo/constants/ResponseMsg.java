package com.vkstech.mongoDbDemo.constants;

public interface ResponseMsg {

    String BOOK_ALREADY_EXISTS = "Book with this id already exist.";
    String BOOK_SAVED = "Book saved successfully.";
    String BOOK_NOT_EXISTS = "Book does not exist.";
    String BOOK_FETCHED = "Book fetched successfully.";
    String BOOKS_FETCHED = "Books fetched successfully.";
    String BOOK_DELETED = "Book deleted successfully.";

    String USER_ALREADY_EXISTS = "User with this email already exist.";
    String USER_SAVED = "User saved successfully.";
    String USER_FETCHED = "Users fetched successfully.";
}
