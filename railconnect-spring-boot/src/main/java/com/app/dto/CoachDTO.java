package com.app.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.app.entities.TrainEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachDTO {
//    private String coachType; // You may need to adjust this based on how coach information is represented
    // Add other fields if necessary
    

	private Long coachId;

	private int firstClass;
	private int secondClass;
	private int sleeper;
	private int thirdAC;
	private int chairCar;
}
