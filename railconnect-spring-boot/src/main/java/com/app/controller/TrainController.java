package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddTrainDTO;
import com.app.dto.SearchTrainDTO;
import com.app.service.TrainService;

@RestController
@RequestMapping("/trains")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrainController {

	private final TrainService trainService;

	public TrainController(TrainService trainService) {
		this.trainService = trainService;
	}

	// Any user can view the trains
	@CrossOrigin(origins = "*")
	@GetMapping("/view")
	public List<AddTrainDTO> viewTrains() {
		return trainService.getAllTrains();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/search")
	public List<SearchTrainDTO> viewTrainsBySrcAndDes(@RequestParam("source") String src,
			@RequestParam("destination") String des,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfJourney) {

		return trainService.getTrainsBySrcDescDate(src, des, dateOfJourney);
	}

	// Admin can add trains
	@PostMapping("/add")
	// @PreAuthorize("hasRole('ADMIN')")
	public AddTrainDTO addTrain(@Valid @RequestBody AddTrainDTO trainDTO) {
		return trainService.addTrain(trainDTO);
	}

	// Admin can remove trains (make them inactive)
	@PutMapping("/remove/{trainNumber}")
	// @PreAuthorize("hasRole('ADMIN')")
	@CrossOrigin(origins = "*")
	public void removeTrain(@PathVariable long trainNumber) {
		trainService.removeTrain(trainNumber);
	}

	@PutMapping("/activate/{trainNumber}")
	@CrossOrigin(origins = "*")
	public void activateTrain(@PathVariable long trainNumber) {
		trainService.activateTrain(trainNumber);
	}

	// Update the cancelTrainAndReschedule method to handle the cancelDate parameter
	@PutMapping("/{trainNumber}/cancel")
	public ResponseEntity<String> cancelTrainAndReschedule(@PathVariable Long trainNumber,
			@RequestParam("cancelDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cancelDate) {
		trainService.cancelTrainAndReschedule(trainNumber, cancelDate);
		return ResponseEntity.ok("Train canceled for " + cancelDate.toString() + " and rescheduled successfully");
	}

}
