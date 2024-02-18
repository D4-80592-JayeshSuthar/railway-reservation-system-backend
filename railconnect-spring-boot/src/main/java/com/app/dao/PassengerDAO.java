package com.app.dao;
import com.app.entities.PassengerEntity;
import com.app.entities.TicketEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDAO extends JpaRepository<PassengerEntity, Long> {

	Optional<PassengerEntity> findByTicket(TicketEntity ticket);
    // You can define custom query methods if needed
}
