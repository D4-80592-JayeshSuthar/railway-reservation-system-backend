package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.BookingDTO;
import com.app.dto.NewBookingRequestDTO;
import com.app.dto.NewBookingResponseDTO;
import com.app.entities.BookingEntity;
import com.app.entities.RefundEntity;


@Service
public interface BookingService {
	
	List<BookingDTO> findUserBookings(Long userId);
	
	List<BookingDTO> showAllBookings();
	
	BookingDTO addNewBooking(BookingDTO booking);

}
