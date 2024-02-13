package com.app.dao;

import com.app.entities.RefundEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundDAO extends JpaRepository<RefundEntity, Long> {
    // You can define custom query methods if needed
}
