package com.app.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserNotActiveException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotActiveException(String message) {
        super(message);
    }
}

