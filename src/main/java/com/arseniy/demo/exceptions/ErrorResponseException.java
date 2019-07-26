package com.arseniy.demo.exceptions;

public class ErrorResponseException extends RuntimeException {
    public ErrorResponseException(String message) {
        super(message);
    }
}
