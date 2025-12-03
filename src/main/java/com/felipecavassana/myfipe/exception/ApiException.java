package com.felipecavassana.myfipe.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}