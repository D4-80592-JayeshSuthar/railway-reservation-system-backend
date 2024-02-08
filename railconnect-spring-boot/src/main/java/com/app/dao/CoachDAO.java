package com.app.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.CoachEntity;

@Repository
public interface CoachDAO extends JpaRepository<CoachEntity, Long> {
    Optional<CoachEntity> findByCoachId(Long coachId);
}
