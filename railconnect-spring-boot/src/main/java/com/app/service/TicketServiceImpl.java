package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TicketDAO;
import com.app.dto.TicketDTO;
import com.app.entities.PassengerEntity;
import com.app.entities.TicketEntity;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDAO ticketDao;

	@Override
	public Optional<TicketEntity> findById(Long ticketId) {
		return ticketDao.findById(ticketId);
	}

	@Override
	public TicketEntity bookTicket(TicketEntity ticket) {
		ticket.getStatus();
		return ticketDao.save(ticket);
	}

	@Override
	public List<TicketEntity> findByPnr(Long pnr) {
		return ticketDao.findByBookingPnrNumber(pnr);
	}

	public static TicketEntity convertDtoToEntity(TicketDTO ticketDTO) {
		TicketEntity ticketEntity = new TicketEntity();
		ticketEntity.setId(ticketDTO.getTicketId());
		ticketEntity.setSeatNumber(ticketDTO.getSeatNumber());
		ticketEntity.setStatus(ticketDTO.getStatus());
		// Conversion of PassengerDTO to PassengerEntity
		ticketEntity.setPassengerEntity(new PassengerEntity(ticketDTO.getPassenger().getPassengerName(),
				ticketDTO.getPassenger().getGender(), ticketDTO.getPassenger().getPassengerAge(), ticketEntity));
		return ticketEntity;
	}

}
