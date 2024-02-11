package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDTO;
import com.app.service.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin
@Validated
public class BookingController {

	@Autowired
	private BookingService bookingService;

//	@GetMapping("/mybookings")
//	public List<BookingDTO> showMyBookings(@RequestHeader("userId") Long userId) {
//		return bookingService.findUserBookings(userId);
//	}
	
	@GetMapping("/mybookings")
	public List<BookingDTO> showMyBookings(@RequestHeader("userId") Long userId) {
	    List<BookingDTO> bookings = bookingService.findUserBookings(userId);
	    // Populate passenger details, user ID, train number, and seat information for each booking
	    for (BookingDTO booking : bookings) {
//	        booking.setPassengers(bookingService.getPassengersByPnrNumber(booking.getPnrNumber()));
	        booking.setUserId(userId);
//	        booking.setTrainNumber(bookingService.getTrainNumberByPnrNumber(booking.getPnrNumber()));
//	        booking.setSeats(bookingService.getSeatsByPnrNumber(booking.getPnrNumber()));
	    }
	    return bookings;
	}

//	@GetMapping("/allbookings")
//	public List<BookingDTO> showAllBookings() {
//		return bookingService.showAllBookings();
//	}
	
	@GetMapping("/allbookings")
	public List<BookingDTO> showAllBookings() {
	    List<BookingDTO> bookings = bookingService.showAllBookings();
	    // Populate passenger details, user ID, train number, and seat information for each booking
	    for (BookingDTO booking : bookings) {
//	        booking.setPassengers(bookingService.getPassengersByPnrNumber(booking.getPnrNumber()));
//	        booking.setTrainNumber(bookingService.getTrainNumberByPnrNumber(booking.getPnrNumber()));
//	        booking.setSeats(bookingService.getSeatsByPnrNumber(booking.getPnrNumber()));
	    }
	    return bookings;
	}

//	@PostMapping("/addnewbooking")
//	public BookingDTO bookTicktes(@RequestBody BookingDTO bookinDto) {
//		return bookingService.addNewBooking(bookinDto);
//	}

	@PostMapping("/addnewbooking")
	public ResponseEntity<BookingDTO> bookTickets(@RequestBody BookingDTO bookingDTO) {
		BookingDTO bookedDTO = bookingService.addNewBooking(bookingDTO);
		return new ResponseEntity<>(bookedDTO, HttpStatus.CREATED);
	}
}
