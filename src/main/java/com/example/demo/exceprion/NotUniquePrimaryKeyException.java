package com.example.demo.exceprion;

public class NotUniquePrimaryKeyException extends RuntimeException{
    public NotUniquePrimaryKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}