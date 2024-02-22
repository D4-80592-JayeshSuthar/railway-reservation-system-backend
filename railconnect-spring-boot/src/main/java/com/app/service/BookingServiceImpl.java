package com.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BookingDAO;
import com.app.dao.PassengerDAO;
import com.app.dao.TicketDAO;
import com.app.dao.TrainDAO;
import com.app.dao.UserEntityDao;
import com.app.dto.BookingDTO;
import com.app.dto.PassengerDTO;
import com.app.dto.TicketDTO;
import com.app.entities.BookingEntity;
import com.app.entities.PassengerEntity;
import com.app.entities.TicketEntity;
import com.app.entities.TicketStatus;
import com.app.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDAO bookingDao;

	@Autowired
	private ModelMapper modelMapper; // Inject ModelMapper bean

	@Autowired
	private TicketDAO ticketDao;

	@Autowired
	private PassengerDAO passengerDao;

	@Autowired
	private TrainDAO trainDao;

	@Autowired
	private UserEntityDao userDao;

	public BookingEntity convertDtoToEntity(BookingDTO bookingDTO) {

		BookingEntity newBooking = modelMapper.map(bookingDTO, BookingEntity.class);
		System.out.println("NewBooking = " + newBooking.toString());
		newBooking.setUser(userDao.findById(bookingDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User Not found")));
		newBooking.setTrain(trainDao.findById(bookingDTO.getTrainNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Train Not found")));
		Set<TicketDTO> tickets = bookingDTO.getTickets();
		for (TicketDTO ticket : tickets) {
			newBooking.getTickets().add(TicketServiceImpl.convertDtoToEntity(ticket));
		}
		return newBooking;
	}

	@SuppressWarnings("null")
	@Override
	public List<BookingDTO> findUserBookings(Long userId) {
		List<BookingEntity> userBookings = bookingDao.findByUserId(userId);
		List<BookingDTO> userBookingDTOs = new ArrayList<>();

		for (BookingEntity booking : userBookings) {
			BookingDTO bookingDTO = convertEntityToDto(booking);
			userBookingDTOs.add(bookingDTO);
		}
		return userBookingDTOs;
	}

	public BookingDTO convertEntityToDto(BookingEntity bookingEntity) {
		BookingDTO bookingDTO = modelMapper.map(bookingEntity, BookingDTO.class);
		bookingDTO.setUserId(bookingEntity.getUser().getId());
		bookingDTO.setTrainNumber(bookingEntity.getTrain().getTrainNumber());

		Set<TicketDTO> tickets = new HashSet<>();
		for (TicketEntity ticketEntity : bookingEntity.getTickets()) {
			TicketDTO ticketDTO = convertEntityToDto(ticketEntity);
			tickets.add(ticketDTO);
		}
		bookingDTO.setTickets(tickets);
		return bookingDTO;
	}

	public TicketDTO convertEntityToDto(TicketEntity ticketEntity) {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setTicketId(ticketEntity.getId());
		ticketDTO.setSeatNumber(ticketEntity.getSeatNumber());
		ticketDTO.setStatus(ticketEntity.getStatus());

		PassengerDTO passengerDTO = new PassengerDTO();
		Optional<PassengerEntity> passengerEntityOptional = passengerDao.findByTicket(ticketEntity);
		if (passengerEntityOptional.isPresent()) {
			PassengerEntity passengerEntity = passengerEntityOptional.get();
			passengerDTO.setPassengerName(passengerEntity.getPassengerName());
			passengerDTO.setGender(passengerEntity.getGender());
			passengerDTO.setPassengerAge(passengerEntity.getPassengerAge());
		}
		ticketDTO.setPassenger(passengerDTO);
		return ticketDTO;
	}

//	@SuppressWarnings("null")
	@Override
	public List<BookingDTO> showAllBookings() {
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		List<BookingEntity> bookingEntities = bookingDao.findAll();

		for (BookingEntity bookingEntity : bookingEntities) {
			bookingDTOs.add(convertEntityToDto(bookingEntity));
		}
		return bookingDTOs;
	}// Getting all ticket information is not implemented in this method

	// final add booking which is working

	@Override
	public BookingDTO addNewBooking(BookingDTO booking) {
		// Convert DTO to entity
		BookingEntity newBooking = convertDtoToEntity(booking);

		// Save the new booking entity
		bookingDao.save(newBooking);

		// Convert the saved entity back to DTO
		BookingDTO savedBookingDTO = convertEntityToDto(newBooking);

		// Convert and save tickets
		List<TicketEntity> ticketEntities = new ArrayList<>();

		int acCount = 0;
		int sleeperCount = 0;
		int generalCount = 0;
		
		Optional<Integer> acOpt = trainDao.findAcCoachCountByTrainNumberAndDateOfJourney(newBooking.getTrain().getTrainNumber()
				, newBooking.getDateOfJourney());
		Optional<Integer> sleeperOpt = trainDao.findSleeperCoachCountByTrainNumberAndDateOfJourney(newBooking.getTrain().getTrainNumber()
				, newBooking.getDateOfJourney());
		Optional<Integer> generalOpt = trainDao.findGeneralCoachCountByTrainNumberAndDateOfJourney(newBooking.getTrain().getTrainNumber()
				, newBooking.getDateOfJourney());
		
		if(acOpt.isPresent())
			acCount = acOpt.get();
		if(sleeperOpt.isPresent())
			sleeperCount = sleeperOpt.get();
		if(generalOpt.isPresent())
			generalCount = generalOpt.get();
		
		System.out.println("Counts : "+acCount+sleeperCount+generalCount);
		
		// Iterate through each ticket in the booking
		for (TicketDTO ticketDTO : booking.getTickets()) {
			TicketEntity ticketEntity = TicketServiceImpl.convertDtoToEntity(ticketDTO);
			ticketEntity.setBooking(newBooking);

			// Allocate seat number based on coach type availability
			switch (newBooking.getCoachType()) {
			case AC:
				if (acCount < newBooking.getTrain().getAcSeats()) {
					ticketEntity.setSeatNumber("AC" + (++acCount));
					ticketEntity.setStatus(TicketStatus.CONFIRM);
				} else {
					ticketEntity.setStatus(TicketStatus.WAITING);
				}
				break;
			case SLEEPER:
				if (sleeperCount < newBooking.getTrain().getSleeperSeats()) {
					ticketEntity.setSeatNumber("SL" + (++sleeperCount));
					ticketEntity.setStatus(TicketStatus.CONFIRM);
				} else {
					ticketEntity.setStatus(TicketStatus.WAITING);
				}
				break;
			case GENERAL:
				if (generalCount < newBooking.getTrain().getGeneralSeats()) {
					ticketEntity.setSeatNumber("GN" + (++generalCount));
					ticketEntity.setStatus(TicketStatus.CONFIRM);
				} else {
					ticketEntity.setStatus(TicketStatus.WAITING);
				}
				break;
			default:
				// Handle invalid coach type
				break;
			}

			// Save the ticket entity
			ticketDao.save(ticketEntity);
			ticketEntities.add(ticketEntity);

		}
		return savedBookingDTO;
	}

}
