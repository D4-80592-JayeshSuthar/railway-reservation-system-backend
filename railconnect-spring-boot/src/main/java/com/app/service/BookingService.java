// BookingService.java

package com.app.service;

import java.util.List;

import com.app.dto.BookingDTO;

public interface BookingService {

    List<BookingDTO> findUserBookings(Long userId);

    List<BookingDTO> showAllBookings();

    BookingDTO addNewBooking(BookingDTO booking);
}
