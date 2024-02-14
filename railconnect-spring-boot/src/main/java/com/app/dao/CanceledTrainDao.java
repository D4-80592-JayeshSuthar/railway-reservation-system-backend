package com.app.dao;

import java.time.LocalDate;
import java.util.Optional;

import com.app.entities.CanceledTrainEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanceledTrainDao extends JpaRepository<CanceledTrainEntity, Long> {
    
    Optional<CanceledTrainEntity> findByTrainNumberAndCancelDate(Long trainNumber, LocalDate cancelDate);
}
