package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.entities.TicketEntity;

@Service
public interface TicketService {

	Optional<TicketEntity> findById(Long ticketId);
	TicketEntity bookTicket(TicketEntity ticket);
	
	List<TicketEntity> findByPnr(Long pnr);
}
