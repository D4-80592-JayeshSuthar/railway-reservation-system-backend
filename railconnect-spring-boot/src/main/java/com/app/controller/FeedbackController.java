package com.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FeedbackWithUserEmailDTO;
import com.app.entities.FeedbackRequest;
import com.app.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedbackByUser")
    public ResponseEntity<String> submitFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        try {
            feedbackService.saveFeedback(feedbackRequest);
            return ResponseEntity.ok("Feedback submitted successfully");
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        			.body("Error submitting feedback: " + e.getMessage());
        }
    }
    
    @GetMapping("/getAllFeedback")
    public List<FeedbackWithUserEmailDTO> getAllFeedbackWithUserEmail() {
        return feedbackService.getAllFeedbackList();
    }
    
    
}
