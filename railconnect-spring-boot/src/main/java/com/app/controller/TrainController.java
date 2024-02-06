package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TrainDTO;
import com.app.service.TrainService;

@RestController
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    // Any user can view the trains
    @GetMapping("/view")
    public List<TrainDTO> viewTrains() {
        return trainService.getAllTrains();
    }

    // Admin can add trains
    @PostMapping("/add")
   // @PreAuthorize("hasRole('ADMIN')")
    public TrainDTO addTrain(@Valid @RequestBody TrainDTO trainDTO) {
        return trainService.addTrain(trainDTO);
    }

    // Admin can cancel trains
    @DeleteMapping("/cancel/{trainNumber}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void cancelTrain(@PathVariable long trainNumber) {
        trainService.cancelTrain(trainNumber);
    }
}
