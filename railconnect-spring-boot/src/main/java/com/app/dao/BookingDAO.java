package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.dto.PassengerDTO;
import com.app.dto.SeatDTO;
import com.app.entities.BookingEntity;

@Repository
public interface BookingDAO extends JpaRepository<BookingEntity, Long> {

	List<BookingEntity> findByUserId(Long userId);

//	@Query("SELECT new com.app.dto.PassengerDTO(p.passengerName, p.gender, p.age) FROM Passenger p WHERE p.booking.pnrNumber = :pnrNumber")
//	List<PassengerDTO> getPassengersByPnrNumber(@Param("pnrNumber") Long pnrNumber);

//	 @Query("SELECT b.trainNumberFk FROM BookingW b WHERE b.pnrNumber = :pnrNumber")
//	    Long getTrainNumberByPnrNumber(@Param("pnrNumber") Long pnrNumber);

//	@Query("SELECT new com.app.dto.SeatDTO(s.seatNumber, s.status) FROM Seat s WHERE s.booking.pnrNumber = :pnrNumber")
//	List<SeatDTO> getSeatsByPnrNumber(@Param("pnrNumber") Long pnrNumber);
}
