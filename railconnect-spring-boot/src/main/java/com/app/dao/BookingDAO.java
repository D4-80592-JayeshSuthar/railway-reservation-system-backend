package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.dto.PassengerDTO;
import com.app.dto.TicketDTO;
import com.app.entities.BookingEntity;

@Repository
public interface BookingDAO extends JpaRepository<BookingEntity, Long> {
	
    BookingEntity findByPnrNumber(Long pnrNumber);


	List<BookingEntity> findByUserId(Long userId);

	@Query("SELECT new com.app.dto.PassengerDTO(p.passengerName, p.gender, p.passengerAge) FROM BookingEntity b JOIN b.passengers p WHERE b.pnrNumber = :pnrNumber")
	List<PassengerDTO> getPassengersByPnrNumber(@Param("pnrNumber") Long pnrNumber);



	@Query("SELECT b.train FROM BookingEntity b WHERE b.pnrNumber = :pnrNumber") // Corrected entity name
	Long getTrainNumberByPnrNumber(@Param("pnrNumber") Long pnrNumber);


	@Query("SELECT new com.app.dto.TicketDTO(t.ticketId, t.seatNumber, t.status) FROM TicketEntity t WHERE t.booking.pnrNumber = :pnrNumber")
	List<TicketDTO> getSeatsByPnrNumber(@Param("pnrNumber") Long pnrNumber);

}
