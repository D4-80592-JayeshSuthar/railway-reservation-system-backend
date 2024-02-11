package com.app.dao;
import com.app.entities.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDAO extends JpaRepository<PassengerEntity, Long> {
    // You can define custom query methods if needed
}
