package com.app.custom_exceptions;
public class TicketNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(Long ticketId) {
        super("Ticket not found with ID: " + ticketId);
    }
}
