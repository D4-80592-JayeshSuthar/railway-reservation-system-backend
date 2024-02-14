package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserEntityDao;
import com.app.dto.FeedbackWithUserEmailDTO;
import com.app.entities.FeedbackEntity;
import com.app.entities.FeedbackRequest;
import com.app.entities.UserEntity;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;
	 
	@Autowired
	private UserEntityDao userDao;

	@Override
	public void saveFeedback(FeedbackRequest feedbackRequest) {
		 FeedbackEntity feedbackEntity = new FeedbackEntity();
	        feedbackEntity.setFeedback(feedbackRequest.getFeedback());

	        // You may need to fetch the user entity from the database based on userId
	        // For simplicity, let's assume you have a method to retrieve the user entity
	        // from the database and set it in the feedback entity
	        UserEntity user = userDao.findById(feedbackRequest.getUserId()).orElse(null);
	        feedbackEntity.setUser(user);

	        feedbackDao.save(feedbackEntity);

	}

	public List<FeedbackWithUserEmailDTO> getAllFeedbackList() {
        List<FeedbackEntity> feedbackEntities = feedbackDao.findAll();
        List<FeedbackWithUserEmailDTO> feedbackDTOs = new ArrayList<>();
        
        for (FeedbackEntity feedbackEntity : feedbackEntities) {
            FeedbackWithUserEmailDTO dto = mapToDTO(feedbackEntity);
            feedbackDTOs.add(dto);
        }
        
        return feedbackDTOs;
    }

    private FeedbackWithUserEmailDTO mapToDTO(FeedbackEntity feedbackEntity) {
        FeedbackWithUserEmailDTO dto = new FeedbackWithUserEmailDTO();
        dto.setId(feedbackEntity.getId());
        dto.setFeedback(feedbackEntity.getFeedback());
        
        // Fetch user's email using the associated user ID
        UserEntity user = feedbackEntity.getUser();
        if (user != null) {
            dto.setUserId(user.getId());
            dto.setEmail(user.getEmail());
        }
 
        return dto;
    }

}
