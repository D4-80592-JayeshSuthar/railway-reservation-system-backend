package com.app.dto;

import org.springframework.http.HttpStatus;

public class RefundResposeDTO {
	    private HttpStatus status; // HTTP status code
	    private String message; // Message describing the response

	    public RefundResposeDTO(HttpStatus status, String message) {
	        this.status = status;
	        this.message = message;
	    }

}
