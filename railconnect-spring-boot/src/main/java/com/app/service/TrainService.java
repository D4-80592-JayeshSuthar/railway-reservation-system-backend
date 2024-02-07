package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CoachDAO;
import com.app.dao.RouteDAO;
import com.app.dao.TrainDAO;
import com.app.dto.CoachDTO;
import com.app.dto.TrainDTO;
import com.app.entities.CoachEntity;
import com.app.entities.Coaches;
import com.app.entities.RouteEntity;
import com.app.entities.TrainEntity;
import com.app.exceptions.ResourceNotFoundException;

@Service
public class TrainService {

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private CoachDAO coachDAO;
    
    @Autowired
    private RouteService routeService;

    public List<TrainDTO> getAllTrains() {
        return trainDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TrainDTO getTrainById(Long trainNumber) {
        TrainEntity trainEntity = trainDAO.findById((long) trainNumber).orElseThrow(() -> new ResourceNotFoundException("Train not found"));
        return convertToDTO(trainEntity);
    }

    public TrainDTO addTrain(TrainDTO trainDTO) {
        // Convert TrainDTO to TrainEntity
        TrainEntity trainEntity = convertToEntity(trainDTO);

        // Set RouteEntity in TrainEntity
        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setSource(trainDTO.getDepartureStation());
        routeEntity.setDestination(trainDTO.getArrivalStation());
        // Assuming intermediate stations can be added here as well
        routeEntity.setIntermediate("Intermediate stations");

        // Save RouteEntity to get Route ID
        routeEntity = routeDAO.save(routeEntity);

        // Set the generated Route ID in TrainEntity
        trainEntity.setRoute(routeEntity);

        // Save TrainEntity to get Train ID
        trainEntity = trainDAO.save(trainEntity);

        // Add Coaches to Coach table
        addCoachesToTrain(trainDTO, trainEntity);

        // Convert TrainEntity back to TrainDTO and return
        return convertToDTO(trainEntity);
    }

    private void addCoachesToTrain(TrainDTO trainDTO, TrainEntity trainEntity) {
        // Iterate over the coachDTOs in TrainDTO and add them to CoachEntity
        for (String coachType : trainDTO.getClassTypes().keySet()) {
        	
            int seatCapacity = Integer.parseInt(trainDTO.getClassTypes().get(coachType));

            // Create CoachEntity and set its properties
            CoachEntity coachEntity = new CoachEntity();
            coachEntity.setTrain(trainEntity);
            coachEntity.setFirstClass(coachType.equals("FIRSTCLASS") ? seatCapacity : 0);
            coachEntity.setSecondClass(coachType.equals("SECONDCLASS") ? seatCapacity : 0);
            coachEntity.setSleeper(coachType.equals("SLEEPER") ? seatCapacity : 0);
            coachEntity.setThirdAC(coachType.equals("THIRDAC") ? seatCapacity : 0);
            coachEntity.setChairCar(coachType.equals("CHAIRCAR") ? seatCapacity : 0);

            // Save CoachEntity to persist it in the database
            coachDAO.save(coachEntity);
        }
    }

    
    public void cancelTrain(Long trainNumber) {
        TrainEntity trainEntity = trainDAO.findById((long) trainNumber).orElseThrow(() -> new ResourceNotFoundException("Train not found"));
        trainEntity.setCancelStatus(true);
        trainEntity.setActiveStatus(false);
        trainDAO.save(trainEntity);
    }
    
    public void removeTrain(Long trainNumber) {
    	TrainEntity trainEntity = trainDAO.findById((long) trainNumber).orElseThrow(() -> new ResourceNotFoundException("Train not found"));
    	trainEntity.setCancelStatus(true);
    	trainEntity.setActiveStatus(false);
    	trainDAO.save(trainEntity);
    }
    
    
    
    // Service method to cancel a train and reschedule it to a particular date
    public void cancelTrainAndReschedule(Long trainNumber, LocalDate rescheduleDate) {
        TrainEntity trainEntity = trainDAO.findById(trainNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Train not found"));
        
        // Set cancelStatus and activeStatus
        trainEntity.setCancelStatus(true);
        trainEntity.setActiveStatus(false);
        
        // Reschedule the train to the provided date
        trainEntity.setRunningDate(rescheduleDate);
        
        // Save the updated trainEntity
        trainDAO.save(trainEntity);
    }

    
    public void scheduleTrainAfterJourneyCompletion(Long trainNumber, String runsOn) {
        // Logic to calculate the next running date based on runsOn and current date
        LocalDate nextRunningDate = calculateNextRunningDate(runsOn);
        
        TrainEntity trainEntity = trainDAO.findById(trainNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Train not found"));
        
        // Update the running date of the train
        trainEntity.setRunningDate(nextRunningDate);
        
        // Set cancelStatus to false and activeStatus to true
        trainEntity.setCancelStatus(false);
        trainEntity.setActiveStatus(true);
        
        // Save the updated trainEntity
        trainDAO.save(trainEntity);
    }
    
    private LocalDate calculateNextRunningDate(String runsOn) {
        // Logic to calculate the next running date based on runsOn and current date
        // Implement your logic here
        return LocalDate.now().plusDays(7); // Example: Adding 7 days for weekly scheduling
    }

    

    // Method to toggle the status of a train when it starts running
    public void toggleTrainStatusWhenRunning() {
        // Get all trains from the database
        List<TrainEntity> allTrains = trainDAO.findAll();

        // Iterate over each train
        for (TrainEntity train : allTrains) {
            // Check if the train is canceled and inactive
            if (train.isCancelStatus() && !train.isActiveStatus()) {
                // Check if the current date matches the scheduled running date
                if (LocalDate.now().isEqual(train.getRunningDate())) {
                    // Toggle the cancelStatus to false and activeStatus to true
                    train.setCancelStatus(false);
                    train.setActiveStatus(true);

                    // Save the updated train entity
                    trainDAO.save(train);
                }
            }
        }
    }
    

    private TrainDTO convertToDTO(TrainEntity trainEntity) {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setTrainNumber(trainEntity.getTrainNumber());
        trainDTO.setTrainName(trainEntity.getTrainName());
        trainDTO.setArrivalTime(trainEntity.getArrivalTime());
        trainDTO.setDepartureTime(trainEntity.getDepartureTime());
        trainDTO.setRunningDate(trainEntity.getRunningDate());
        trainDTO.setBaseFare(trainEntity.getBaseFare());
        trainDTO.setActiveStatus(trainEntity.isActiveStatus());
        trainDTO.setCancelStatus(trainEntity.isCancelStatus());
        trainDTO.setRouteId(trainEntity.getRoute().getRouteId());
        trainDTO.setRunsOn(trainEntity.getRunsOn());
        trainDTO.setScheduleLink(trainEntity.getScheduleLink());
        trainDTO.setDepartureStation(trainEntity.getDepartureStation());
        trainDTO.setArrivalStation(trainEntity.getArrivalStation());
        trainDTO.setDuration(trainEntity.getDuration());

        // Set the coachDTO
        if (trainEntity.getCoach() != null) {
            CoachDTO coachDTO = new CoachDTO();
            coachDTO.setCoachType(trainEntity.getCoach().getCoachType().toString());
            trainDTO.setCoachDTO(coachDTO);
        }

        return trainDTO;
    }

    private TrainEntity convertToEntity(TrainDTO trainDTO) {
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setTrainNumber(trainDTO.getTrainNumber());
        trainEntity.setTrainName(trainDTO.getTrainName());
        trainEntity.setArrivalTime(trainDTO.getArrivalTime());
        trainEntity.setDepartureTime(trainDTO.getDepartureTime());
        trainEntity.setRunningDate(trainDTO.getRunningDate());
        trainEntity.setBaseFare(trainDTO.getBaseFare());
        trainEntity.setActiveStatus(trainDTO.isActiveStatus());
        trainEntity.setCancelStatus(trainDTO.isCancelStatus());
        trainEntity.setRunsOn(trainDTO.getRunsOn());
        trainEntity.setScheduleLink(trainDTO.getScheduleLink());
        trainEntity.setDepartureStation(trainDTO.getDepartureStation());
        trainEntity.setArrivalStation(trainDTO.getArrivalStation());
        trainEntity.setDuration(trainDTO.getDuration());

        // Set the coach
        if (trainDTO.getCoachDTO() != null && trainDTO.getCoachDTO().getCoachType() != null) {
            trainEntity.setCoachType(Coaches.valueOf(trainDTO.getCoachDTO().getCoachType()));
        }

        return trainEntity;
    }


}
