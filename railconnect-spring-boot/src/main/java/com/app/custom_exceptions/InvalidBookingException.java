package com.app.custom_exceptions;

public class InvalidBookingException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBookingException(String message) {
        super(message);
    }
}
