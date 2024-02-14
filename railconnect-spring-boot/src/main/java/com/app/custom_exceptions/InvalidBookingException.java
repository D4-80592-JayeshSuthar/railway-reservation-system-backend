package com.app.custom_exceptions;

public class InvalidBookingException extends RuntimeException {

    public InvalidBookingException(String message) {
        super(message);
    }
}
