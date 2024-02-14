package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.TrainDAO;
import com.app.dto.AddTrainDTO;
import com.app.dto.SearchTrainDTO;
import com.app.service.TrainService;

@RestController
@RequestMapping("/trains")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrainController {

    private final TrainService trainService;
    
    @Autowired
	private TrainDAO trainDAO;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    // Any user can view the trains	
    @CrossOrigin(origins = "*")
    @GetMapping("/view")
    public List<AddTrainDTO> viewTrains() {
        return trainService.getAllTrains();
    }
    
    
//    @GetMapping("/search")
//    public List<SearchTrainDTO> viewTrainsBySrcAndDes(
//            @RequestParam("source") String src,
//            @RequestParam("destination") String des,
//            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfJourney) {
//        
//        return trainService.getTrainsBySrcDescDate(src, des, dateOfJourney);
//    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/search")
    public List<SearchTrainDTO> viewTrainsBySrcAndDes(
            @RequestParam("source") String src,
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

    
    //------------------------------------------Old Method---------------------------------------
//    // Admin can cancel trains
//    @DeleteMapping("/cancel/{trainNumber}")
//    //@PreAuthorize("hasRole('ADMIN')")
//    public void cancelTrain(@PathVariable long trainNumber) {
//        trainService.cancelTrain(trainNumber);
//    }
    
    
//    // Admin can remove trains (make them inactive)
//    @DeleteMapping("/remove/{trainNumber}")
//    //@PreAuthorize("hasRole('ADMIN')")
//    public void removeTrain(@PathVariable long trainNumber) {
//    	trainService.removeTrain(trainNumber);
//    }
//    
//    
//    
// // Controller method to cancel a train and reschedule it to a particular date
//    @PutMapping("/{trainNumber}/cancel")
//    public ResponseEntity<String> cancelTrainAndReschedule(@PathVariable Long trainNumber, @RequestParam("rescheduleDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rescheduleDate) {
////        trainService.cancelTrainAndReschedule(trainNumber, rescheduleDate);
//        return ResponseEntity.ok("Train canceled and rescheduled successfully");
//    }
//
//
//    
//    // Controller method to schedule a train after journey completion according to respective days
//    @PutMapping("/{trainNumber}/schedule")
//    public ResponseEntity<String> scheduleTrainAfterJourneyCompletion(@PathVariable Long trainNumber) {
//       
//		TrainEntity trainEntity = trainDAO.findById(trainNumber)
//            .orElseThrow(() -> new ResourceNotFoundException("Train not found"));
////        trainService.scheduleTrainAfterJourneyCompletion(trainNumber, trainEntity.getRunsOn());
//        return ResponseEntity.ok("Train scheduled after journey completion successfully");
//    }
    
    //-----------------------------------------------------------------------------
 
    
    // Admin can remove trains (make them inactive)
    @PutMapping("/remove/{trainNumber}")
    //@PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*")
    public void removeTrain(@PathVariable long trainNumber) {
        trainService.removeTrain(trainNumber);
    }
    
    @PutMapping("/activate/{trainNumber}")
    @CrossOrigin(origins = "*")
    public void activateTrain(@PathVariable long trainNumber) {
        trainService.activateTrain(trainNumber);
    }

//    // Controller method to cancel a train and reschedule it to a particular date
//    @PutMapping("/{trainNumber}/cancel")
//    public ResponseEntity<String> cancelTrainAndReschedule(@PathVariable Long trainNumber, @RequestParam("rescheduleDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rescheduleDate) {
//        trainService.cancelTrainAndReschedule(trainNumber, rescheduleDate.getDayOfWeek().toString());
//        return ResponseEntity.ok("Train canceled and rescheduled successfully");
//    }
    
 // Update the cancelTrainAndReschedule method to handle the cancelDate parameter
    @PutMapping("/{trainNumber}/cancel")
    public ResponseEntity<String> cancelTrainAndReschedule(@PathVariable Long trainNumber, @RequestParam("cancelDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cancelDate) {
        trainService.cancelTrainAndReschedule(trainNumber, cancelDate);
        return ResponseEntity.ok("Train canceled for " + cancelDate.toString() + " and rescheduled successfully");
    }


//    // Controller method to schedule a train after journey completion according to respective days
//    @PutMapping("/{trainNumber}/schedule")
//    public ResponseEntity<String> scheduleTrainAfterJourneyCompletion(@PathVariable Long trainNumber) {
//        TrainEntity trainEntity = trainDAO.findById(trainNumber)
//            .orElseThrow(() -> new ResourceNotFoundException("Train not found"));
//        trainService.scheduleTrainAfterJourneyCompletion(trainNumber, trainEntity.getRunsOn());
//        return ResponseEntity.ok("Train scheduled after journey completion successfully");
//    }
    
}
