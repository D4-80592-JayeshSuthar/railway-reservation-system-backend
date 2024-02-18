package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.ValueHandlerFactory.FloatValueHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.CancellationNotAllowedException;
import com.app.custom_exceptions.InvalidBookingException;
import com.app.custom_exceptions.TicketNotFoundException;
import com.app.dao.BookingDAO;
import com.app.dao.PassengerDAO;
import com.app.dao.RefundDAO;
//import com.app.dao.RouteDAO;
import com.app.dao.TicketDAO;
import com.app.dao.TrainDAO;
import com.app.dao.UserEntityDao;
//import com.app.dao.TrainDAO;
import com.app.dto.BookingDTO;
import com.app.dto.PassengerDTO;
import com.app.dto.SeatAvailabilityDTO;
import com.app.dto.TicketDTO;
import com.app.entities.BookingEntity;
import com.app.entities.Coaches;
import com.app.entities.PassengerEntity;
import com.app.entities.RefundEntity;
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
	private RefundDAO refundDao;
	
	

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
    	BookingDTO booking = modelMapper.map(bookingEntity, BookingDTO.class);
    	System.out.println(booking.getTickets().toString());
    	booking.setUserId(bookingEntity.getUser().getId());
    	booking.setTrainNumber(bookingEntity.getTrain().getTrainNumber());
    	booking.setTickets(new HashSet<TicketDTO>());
    	Set<TicketDTO> tickets = booking.getTickets();
    	
    	for (TicketEntity ticket : bookingEntity.getTickets()) {
    		tickets.add(TicketServiceImpl.convertEntityToDto(ticket));
		}
        return booking;
    }
    public BookingEntity convertDtoToEntity(BookingDTO bookingDTO) {
       
    	BookingEntity newBooking = modelMapper.map(bookingDTO, BookingEntity.class);
    	newBooking.setUser(userDao.findById(bookingDTO.getUserId())
    			.orElseThrow(() -> new ResourceNotFoundException("User Not found")));
    	newBooking.setTrain(trainDao.findById(bookingDTO.getTrainNumber())
    			.orElseThrow(() -> new ResourceNotFoundException("Train Not found")));
    	Set<TicketDTO> tickets = bookingDTO.getTickets();
    	for (TicketDTO ticket : tickets) {
			newBooking.getTickets()
			.add(TicketServiceImpl.convertDtoToEntity(ticket));
		}
    	return newBooking;
        
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

	 // Fetch the coach counts for the train on the date of journey
	    Optional<List<Integer[]>> coachCountsOptional = trainDao.findCoachCountByTrainNumberAndDateOfJourney(
	            newBooking.getTrain().getTrainNumber(), newBooking.getDateOfJourney());
	    System.out.println("**************" + coachCountsOptional.toString());

	if (coachCountsOptional.isPresent()) {
	    List<Integer[]> coachCounts = coachCountsOptional.get();
	    int acCount = coachCounts.get(0)[0]; // Assuming ac_count is the first column
	    int sleeperCount = coachCounts.get(0)[1]; // Assuming sleeper_count is the second column
	    int generalCount = coachCounts.get(0)[2]; // Assuming general_count is the third column
	    // Use the counts as needed

	   
	    
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

	            // Add passenger to the ticket
	            PassengerDTO passengerDTO = ticketDTO.getPassenger();
	            PassengerEntity passengerEntity = new PassengerEntity();
	            passengerEntity.setPassengerName(passengerDTO.getPassengerName());
	            passengerEntity.setGender(passengerDTO.getGender());
	            passengerEntity.setPassengerAge(passengerDTO.getPassengerAge());
	            passengerEntity.setTicket(ticketEntity); // Associate passenger with ticket
	            passengerDao.save(passengerEntity);
	            ticketEntity.setPassengerEntity(passengerEntity);
	        }
	    } else {
	        // Handle case where coach counts are not available
	        // Set all tickets to waiting status
	        for (TicketDTO ticketDTO : booking.getTickets()) {
	            TicketEntity ticketEntity = TicketServiceImpl.convertDtoToEntity(ticketDTO);
	            ticketEntity.setBooking(newBooking);
	            ticketEntity.setStatus(TicketStatus.WAITING);

	            // Save the ticket entity
	            ticketDao.save(ticketEntity);
	            ticketEntities.add(ticketEntity);

	            // Add passenger to the ticket
	            PassengerDTO passengerDTO = ticketDTO.getPassenger();
	            PassengerEntity passengerEntity = new PassengerEntity();
	            passengerEntity.setPassengerName(passengerDTO.getPassengerName());
	            passengerEntity.setGender(passengerDTO.getGender());
	            passengerEntity.setPassengerAge(passengerDTO.getPassengerAge());
	            passengerEntity.setTicket(ticketEntity); // Associate passenger with ticket
	            passengerDao.save(passengerEntity);
	            ticketEntity.setPassengerEntity(passengerEntity);
	        }
	    }
		return savedBookingDTO;

	}
	
	
	/*
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
	            .collect(Collectors.toSet()));

	    // Convert and save passengers
//	    List<PassengerEntity> passengerEntities = new ArrayList<>();
//	   
//	    for (PassengerDTO passengerDTO : booking.getTickets().getPassenger()) {
//	        PassengerEntity passengerEntity = new PassengerEntity();
//	        // Map passengerDTO to passengerEntity attributes
//	        passengerEntity.setPassengerName(passengerDTO.getPassengerName());
//	        passengerEntity.setGender(passengerDTO.getGender());
//	        passengerEntity.setPassengerAge(passengerDTO.getPassengerAge());
//	        
//	        // Save the passengerEntity
//	        passengerDao.save(passengerEntity);
//	        passengerEntities.add(passengerEntity);
//	    }
//	    savedBookingDTO.setPassengers(passengerEntities.stream()
//	            .map(passengerEntity -> {
//	                PassengerDTO passengerDTO = new PassengerDTO();
//	                // Map passengerEntity to passengerDTO attributes
//	                passengerDTO.setPassengerName(passengerEntity.getPassengerName());
//	                passengerDTO.setGender(passengerEntity.getGender());
//	                passengerDTO.setPassengerAge(passengerEntity.getPassengerAge());
//	                return passengerDTO;
//	            })
//	            .collect(Collectors.toList()));
	    
	    return savedBookingDTO;
	}

	 
	
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
 */
	
	
	/*
	// working just seat number is getting repeated
	@Override
	public BookingDTO addNewBooking(BookingDTO booking) {
	    // Convert DTO to entity
	    BookingEntity newBooking = convertDtoToEntity(booking);

	    // Calculate the total capacity of the train based on coach type
	    Coaches coach = booking.getCoachType();
	    int totalCapacity = 0;
	    if(coach.toString() == "SLEEPER") {
	    	totalCapacity = newBooking.getTrain().getSleeperSeats();
	    }
	    if(coach.toString() == "AC") {
	    	totalCapacity = newBooking.getTrain().getAcSeats();
	    }
	    if(coach.toString() == "GENERAL") {
	    	totalCapacity = newBooking.getTrain().getGeneralSeats();
	    }

	    // Calculate the number of tickets in this booking
	    int numberOfTickets = booking.getTickets().size();

	    // Check if there are available seats or if waiting list should be used
	    boolean useWaitingList = false;
	    if (totalCapacity - numberOfTickets < 0) {
	        useWaitingList = true;
	    }

	    // Save the new booking entity
	    bookingDao.save(newBooking);

	    // Convert the saved entity back to DTO
	    BookingDTO savedBookingDTO = convertEntityToDto(newBooking);

	    // Convert and save tickets
	    List<TicketEntity> ticketEntities = new ArrayList<>();
	    int seatNumber = 1;
	    for (TicketDTO ticketDTO : booking.getTickets()) {
	        TicketEntity ticketEntity = TicketServiceImpl.convertDtoToEntity(ticketDTO);
	        ticketEntity.setBooking(newBooking);
	        
	        // Set seat number based on availability
	        if (!useWaitingList) {
	            if (seatNumber <= totalCapacity) {
	                String coachPrefix = newBooking.getCoachType().toString().substring(0, 2).toUpperCase();
	                ticketEntity.setSeatNumber(coachPrefix + seatNumber++);
	                ticketEntity.setStatus(TicketStatus.CONFIRM);
	            } else {
	                ticketEntity.setStatus(TicketStatus.WAITING);
	            }
	        } else {
	            ticketEntity.setStatus(TicketStatus.WAITING);
	        }

	        // Save the ticket entity
	        
//	        ticketEntities.add(ticketEntity);

	        // Add passengers to the ticket
	        PassengerDTO passengerDTO = ticketDTO.getPassenger();
//	        List<PassengerEntity> passengerEntities = new ArrayList<>();
//	        for (PassengerDTO passengerDTO : passengers) {
	            PassengerEntity passengerEntity = new PassengerEntity();
	            passengerEntity.setPassengerName(passengerDTO.getPassengerName());
	            passengerEntity.setGender(passengerDTO.getGender());
	            passengerEntity.setPassengerAge(passengerDTO.getPassengerAge());
	            passengerEntity.setTicket(ticketEntity); // Associate passenger with ticket
	            passengerDao.save(passengerEntity);
//	            passengerEntities.add(passengerEntity);
	            ticketEntity.setPassengerEntity(passengerEntity);
//	        }
	        ticketDao.save(ticketEntity);
	    }
	    savedBookingDTO.setTickets(ticketEntities.stream()
	            .map(TicketServiceImpl::convertEntityToDto)
	            .collect(Collectors.toSet()));

	    return savedBookingDTO;
	}
	*/
	
	
}
