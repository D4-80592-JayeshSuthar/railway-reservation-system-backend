package com.app.dao;

import com.app.entities.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainDAO extends JpaRepository<TrainEntity, Long> {


}
