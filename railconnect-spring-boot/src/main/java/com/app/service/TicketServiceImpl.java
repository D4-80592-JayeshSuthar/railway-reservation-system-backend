package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TicketDAO;
import com.app.dto.TicketDTO;
import com.app.entities.BookingEntity;
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
		return ticketDao.save(ticket);
	}

	@Override
	public List<TicketEntity> findByPnr(Long pnr) {
		return ticketDao.findByBookingPnrNumber(pnr);
	}
	
	public static TicketEntity convertDtoToEntity(TicketDTO ticketDTO) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketDTO.getTicketId());
        
        // Assuming you have a method to fetch BookingEntity by PNR number
        // For example: bookingRepository.findByPnrNumber(ticketDTO.getPnrNumber());
        // If you don't have the BookingEntity yet, you can set just the PNR number in a placeholder or fetch the entity from the database if necessary
//        BookingEntity booking = new BookingEntity(); // This should be fetched from the database
//        booking.setPnrNumber(ticketDTO.getPnrNumber());
        ticketEntity.setBooking(booking);
        
        ticketEntity.setSeatNumber(ticketDTO.getSeatNumber());
        ticketEntity.setStatus(ticketDTO.getStatus());
//        ticketEntity.setPassengerName(ticketDTO.getPassengerName());
//        ticketEntity.setAadharNumber(ticketDTO.getAadharNumber());
        return ticketEntity;
    }
	
//	public static TicketDTO convertEntityToDto(TicketEntity ticketEntity) {
//        return new TicketDTO(
//            ticketEntity.getId(),
//            ticketEntity.getBooking() != null ? ticketEntity.getBooking().getPnrNumber() : null,
//            ticketEntity.getSeatNumber(),
//            ticketEntity.getStatus(),
//            ticketEntity.getPassengerName(),
//            ticketEntity.getAadharNumber()
//        );
//    }

}
