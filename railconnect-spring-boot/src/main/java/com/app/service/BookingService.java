package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.BookingDTO;


@Service
public interface BookingService {
	
	List<BookingDTO> findUserBookings(Long userId);
	
	List<BookingDTO> showAllBookings();
	
	BookingDTO addNewBooking(BookingDTO booking);
}
