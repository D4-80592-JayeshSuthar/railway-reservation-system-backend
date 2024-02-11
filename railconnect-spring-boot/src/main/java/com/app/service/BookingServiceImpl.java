package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BookingDAO;
import com.app.dao.PassengerDAO;
//import com.app.dao.RouteDAO;
import com.app.dao.TicketDAO;
import com.app.dao.TrainDAO;
import com.app.dao.UserEntityDao;
//import com.app.dao.TrainDAO;
import com.app.dto.BookingDTO;
import com.app.dto.PassengerDTO;
import com.app.dto.SeatDTO;
import com.app.dto.TicketDTO;
import com.app.entities.BookingEntity;
import com.app.entities.PassengerEntity;
import com.app.entities.TicketEntity;

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

//	@Autowired
//	private RouteDAO routeDao;
//
	@Autowired
	private TrainDAO trainDao;

	@Autowired
	private UserEntityDao userDao;
	



	@SuppressWarnings("null")
	@Override
	public List<BookingDTO> findUserBookings(Long userId) {
		// find all the ticket info associated with the respective bookings
		List<BookingEntity> userBookings = bookingDao.findByUserId(userId);

		// for (BookingEntity booking : userBookings) {
//			booking.setTickets(ticketDao.findByBookingPnrNumber(booking.getPnrNumber()));
		// }
		List<BookingDTO> userBookingDTOs = new ArrayList<>();

		for (BookingEntity booking : userBookings) {
			userBookingDTOs.add(convertEntityToDto(booking));
		}

		return userBookingDTOs;
	}

//	public BookingEntity convertDtoToEntity(BookingDTO bookingDTO) {
//		BookingEntity bookingEntity = new BookingEntity();
//		bookingEntity.setPnrNumber(bookingDTO.getPnrNumber());
//		// bookingEntity.setCoach(bookingDTO.getCoach());
//		bookingEntity.setDateOfJourney(bookingDTO.getDateOfJourney());
//		bookingEntity.setFromStation(bookingDTO.getFromStation());
//		bookingEntity.setToStation(bookingDTO.getToStation());
////		bookingEntity.setTrain(new TrainService().getTrainById(bookingDTO.getTrainNumber()));
////		bookingEntity.setUser(new UserServiceImpl().getUserDetailsById(bookingDTO.getUserId()));
//
//		List<TicketEntity> ticketEntities = bookingDTO.getTickets().stream()
//				.map(ticketDTO -> TicketServiceImpl.convertDtoToEntity(ticketDTO)).collect(Collectors.toList());
//
////		bookingEntity.setTickets(ticketEntities);
//
//		return bookingEntity;
//	}

	// Convert BookingEntity to BookingDTO
//	public BookingDTO convertEntityToDto(BookingEntity bookingEntity) {
//		BookingDTO bookingDTO = new BookingDTO();
//		bookingDTO.setPnrNumber(bookingEntity.getPnrNumber());
////		bookingDTO.setCoach(bookingEntity.getCoach());
//		bookingDTO.setUserId(bookingEntity.getUser().getId());
//		bookingDTO.setTrainNumber(bookingEntity.getTrain().getTrainNumber());
//		bookingDTO.setDateOfJourney(bookingEntity.getDateOfJourney());
//		bookingDTO.setFromStation(bookingEntity.getFromStation());
//		bookingDTO.setToStation(bookingEntity.getToStation());
//
////		List<TicketDTO> ticketDTOs = bookingEntity.getTickets().stream()
////				.map(ticketEntity -> TicketServiceImpl.convertEntityToDto(ticketEntity)) // Assuming this method exists
////				.collect(Collectors.toList());
////		bookingDTO.setTickets(ticketDTOs); // This should be a Set<TicketDTO>, not Set<TicketEntity>
//
//		return bookingDTO;
//	}

    public BookingDTO convertEntityToDto(BookingEntity bookingEntity) {
        return modelMapper.map(bookingEntity, BookingDTO.class);
    }

    public BookingEntity convertDtoToEntity(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO, BookingEntity.class);
    }
	
	@SuppressWarnings("null")
	@Override
	public List<BookingDTO> showAllBookings() {
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		List<BookingEntity> bookingEntities = bookingDao.findAll();

		for (BookingEntity bookingEntity : bookingEntities) {
			bookingDTOs.add(convertEntityToDto(bookingEntity));
		}
		return bookingDTOs;
	}// Getting all ticket information is not implemented in this method

	
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
	    for (TicketDTO ticketDTO : booking.getTickets()) {
	        TicketEntity ticketEntity = TicketServiceImpl.convertDtoToEntity(ticketDTO);
	        ticketEntity.setBooking(newBooking);
	        ticketDao.save(ticketEntity);
	        ticketEntities.add(ticketEntity);
	    }
	    savedBookingDTO.setTickets(ticketEntities.stream()
	            .map(TicketServiceImpl::convertEntityToDto)
	            .collect(Collectors.toList()));

	    // Convert and save passengers
	    List<PassengerEntity> passengerEntities = new ArrayList<>();
	    for (PassengerDTO passengerDTO : booking.getPassengers()) {
	        PassengerEntity passengerEntity = new PassengerEntity();
	        // Map passengerDTO to passengerEntity attributes
	        passengerEntity.setPassengerName(passengerDTO.getPassengerName());
	        passengerEntity.setGender(passengerDTO.getGender());
	        passengerEntity.setPassengerAge(passengerDTO.getPassengerAge());
	        
	        // Save the passengerEntity
	        passengerDao.save(passengerEntity);
	        passengerEntities.add(passengerEntity);
	    }
	    savedBookingDTO.setPassengers(passengerEntities.stream()
	            .map(passengerEntity -> {
	                PassengerDTO passengerDTO = new PassengerDTO();
	                // Map passengerEntity to passengerDTO attributes
	                passengerDTO.setPassengerName(passengerEntity.getPassengerName());
	                passengerDTO.setGender(passengerEntity.getGender());
	                passengerDTO.setPassengerAge(passengerEntity.getPassengerAge());
	                return passengerDTO;
	            })
	            .collect(Collectors.toList()));
	    
	    return savedBookingDTO;
	}

	

//    @Override
//    public List<PassengerDTO> getPassengersByPnrNumber(Long pnrNumber) {
//        return bookingDao.getPassengersByPnrNumber(pnrNumber);
//    }

//    @Override
//    public Long getTrainNumberByPnrNumber(Long pnrNumber) {
//        return bookingDao.getTrainNumberByPnrNumber(pnrNumber);
//    }

//    @Override
//    public List<SeatDTO> getSeatsByPnrNumber(Long pnrNumber) {
//        return bookingDao.getSeatsByPnrNumber(pnrNumber);
//    }
	
//	@Override
//	public BookingDTO addNewBooking(BookingDTO booking) {
//
//		BookingEntity newBooking = new BookingEntity();
//		newBooking.setBookingDateTime(booking.getBookingDateTime());
//		if (booking.getCoachType() != null) {
//			newBooking.setCoachType(Coaches.valueOf(booking.getCoachType()));
//		} else {
//			throw new ResourceNotFoundException("coachh not found");
//		}
//		newBooking.setDateOfJourney(booking.getDateOfJourney());
//		newBooking.setFromStation(booking.getFromStation());
//		newBooking.setToStation(booking.getToStation());
//		newBooking.setTotalAmount(booking.getTotalAmount());
//		newBooking.setTrain(trainDao.findById(booking.getTrainNumber())
//				.orElseThrow(() -> new ResourceNotFoundException("Train not found")));
//		newBooking.setUser(userDao.findById(booking.getUserId())
//				.orElseThrow(() -> new ResourceNotFoundException("User not found")));
//
//		bookingDao.save(newBooking);
//		List<TicketDTO> tickets = booking.getTickets();
//		Integer seats = booking.getSeats();
//		// add tickets to database
//		for (TicketDTO ticketDTO : tickets) {
//
//			TicketEntity newTicket = new TicketEntity();
//			newTicket.setBooking(newBooking);
//			newTicket.setStatus(ticketDTO.getStatus());
//			String seatNo = null;
//			if (ticketDTO.getStatus().equals("WAITING")) {
//				seatNo = "NA";
//			} else {
//				seatNo = booking.getCoachType().substring(0, 2).toUpperCase() + seats++;
//			}
//
//			newTicket.setSeatNumber(seatNo);
//
//			ticketDao.save(newTicket);
//
//			// Save Passenger -> PassengerDao I/f
//
//			List<PassengerDTO> passengers = booking.getPassengers();
//
//			// Iterate over the list of passengers
//			for (PassengerDTO passengerDTO : passengers) {
//				PassengerEntity passengerEntity = new PassengerEntity();
//				// Map passengerDTO to passengerEntity attributes
//				passengerEntity.setPassengerName(passengerDTO.getPassengerName());
//				passengerEntity.setGender(passengerDTO.getGender());
//				passengerEntity.setPassengerAge(passengerDTO.getPassengerAge());
//
//				// Save the passengerEntity
//				passengerDao.save(passengerEntity);
//
//				// Associate the passenger with the booking
//				// newBooking.addPassenger(passengerEntity); // Assuming you have a method to
//				// associate a passenger with a booking in BookingEntity
//			}
//
//		}
////		for (TicketDTO ticketDTO : tickets) {
////			ticketDao.save(TicketServiceImpl.convertDtoToEntity(ticketDTO));
////		} // to save the tickets first in the ticket table 
//
//		// Conversion to save the booking info in booking table
//		// BookingEntity bookingEntity = convertDtoToEntity(booking);
//		// BookingEntity newBooking = bookingDao.save(bookingEntity);
//		return convertEntityToDto(newBooking);
//	}

}
