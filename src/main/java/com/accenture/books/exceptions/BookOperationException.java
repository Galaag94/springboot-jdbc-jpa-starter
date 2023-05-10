package com.accenture.books.exceptions;

public class BookOperationException extends RuntimeException{

    public BookOperationException(String message) {
        super(message);
    }
}
