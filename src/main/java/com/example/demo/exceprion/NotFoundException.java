package com.example.demo.exceprion;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}