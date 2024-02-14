package com.app.dao;

import com.app.entities.RefundEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundDAO extends JpaRepository<RefundEntity, Long> {

	List<RefundEntity> findByTicketIdIn(List<Long> ticketIds);
    
}
