package com.app.custom_exceptions;
public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(Long ticketId) {
        super("Ticket not found with ID: " + ticketId);
    }
}
