package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.BookingEntity;

public interface BookingDAO extends JpaRepository<BookingEntity, Long>{

	List<BookingEntity> findByUserId(Long userId);
}
