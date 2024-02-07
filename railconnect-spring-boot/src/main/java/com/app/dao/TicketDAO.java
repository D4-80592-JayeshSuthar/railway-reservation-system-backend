package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.TicketEntity;

public interface TicketDAO extends JpaRepository<TicketEntity, Long>{

	@Query("SELECT t FROM TicketEntity t WHERE t.booking.pnrNumber = ?1")
    List<TicketEntity> findByBookingPnrNumber(Long pnrNumber);
}
