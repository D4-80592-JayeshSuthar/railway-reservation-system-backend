package com.app.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.CoachEntity;

@Repository
public interface CoachDAO extends JpaRepository<CoachEntity, Long> {
    // You can add custom query methods here if needed
}
