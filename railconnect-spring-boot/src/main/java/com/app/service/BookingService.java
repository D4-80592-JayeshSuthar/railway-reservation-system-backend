package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.BookingDTO;
import com.app.dto.PassengerDTO;
import com.app.dto.SeatDTO;


@Service
public interface BookingService {
	
	List<BookingDTO> findUserBookings(Long userId);
	
	List<BookingDTO> showAllBookings();
	
	BookingDTO addNewBooking(BookingDTO booking);

//    List<PassengerDTO> getPassengersByPnrNumber(Long pnrNumber);
    
//    Long getTrainNumberByPnrNumber(Long pnrNumber);
    
//    List<SeatDTO> getSeatsByPnrNumber(Long pnrNumber);
	

}
