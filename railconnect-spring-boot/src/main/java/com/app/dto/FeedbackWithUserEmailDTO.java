package com.app.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackWithUserEmailDTO {
	private Long id;
    private Long userId;
    private String feedback;
    private String email;
}
