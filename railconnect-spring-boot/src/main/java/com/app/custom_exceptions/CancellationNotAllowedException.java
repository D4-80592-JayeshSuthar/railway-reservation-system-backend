package com.app.custom_exceptions;
public class CancellationNotAllowedException extends RuntimeException {

    public CancellationNotAllowedException(String message) {
        super(message);
    }
}
