package com.app.service;

import java.util.List;

import com.app.dto.FeedbackWithUserEmailDTO;
import com.app.entities.FeedbackRequest;

public interface FeedbackService {

	void saveFeedback(FeedbackRequest feedbackRequest);

	List<FeedbackWithUserEmailDTO> getAllFeedbackList();

}
