package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BookingDAO;
import com.app.dao.TicketDAO;
import com.app.dto.BookingDTO;
import com.app.dto.TicketDTO;
import com.app.entities.BookingEntity;
import com.app.entities.TicketEntity;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDao;

    @Autowired
    private TicketDAO ticketDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BookingDTO> findUserBookings(Long userId) {
        List<BookingEntity> userBookings = bookingDao.findByUserId(userId);
        for (BookingEntity booking : userBookings) {
            booking.setTickets(ticketDao.findByBookingPnrNumber(booking.getPnrNumber()));
        }
        return userBookings.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> showAllBookings() {
        List<BookingEntity> bookingEntities = bookingDao.findAll();
        return bookingEntities.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public BookingDTO addNewBooking(BookingDTO bookingDto) {
        List<TicketDTO> tickets = bookingDto.getTickets();
        for (TicketDTO ticketDTO : tickets) {
            ticketDao.save(modelMapper.map(ticketDTO, TicketEntity.class));
        }
        BookingEntity bookingEntity = modelMapper.map(bookingDto, BookingEntity.class);
        BookingEntity newBooking = bookingDao.save(bookingEntity);
        return modelMapper.map(newBooking, BookingDTO.class);
    }

    private BookingDTO convertEntityToDto(BookingEntity bookingEntity) {
        return modelMapper.map(bookingEntity, BookingDTO.class);
    }
}
