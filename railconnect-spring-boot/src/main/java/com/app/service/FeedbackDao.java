package com.app.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.FeedbackEntity;

public interface FeedbackDao extends JpaRepository<FeedbackEntity, Long> {

}
