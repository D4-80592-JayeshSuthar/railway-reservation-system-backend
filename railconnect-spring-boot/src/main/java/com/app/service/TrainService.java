package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TrainDAO;
import com.app.dto.CoachDTO;
import com.app.dto.TrainDTO;
import com.app.entities.RouteEntity;
import com.app.entities.TrainEntity;
import com.app.exceptions.ResourceNotFoundException;

@Service
public class TrainService {

    @Autowired
    private TrainDAO trainDAO;

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
        TrainEntity trainEntity = convertToEntity(trainDTO);
        trainEntity.setRoute(routeService.getRouteById(trainDTO.getRouteId()).toEntity());
        trainEntity = trainDAO.save(trainEntity);
        return convertToDTO(trainEntity);
    }

    public void cancelTrain(Long trainNumber) {
        TrainEntity trainEntity = trainDAO.findById((long) trainNumber).orElseThrow(() -> new ResourceNotFoundException("Train not found"));
        trainEntity.setCancelStatus(true);
        trainEntity.setActiveStatus(false);
        trainDAO.save(trainEntity);
    }

    private TrainDTO convertToDTO(TrainEntity trainEntity) {
        TrainDTO trainDTO = new TrainDTO();
        // Populate existing fields
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
        
      
        CoachDTO coachDTO = new CoachDTO();
        coachDTO.setCoachType(trainEntity.getCoachType().toString()); 
        trainDTO.setCoachDTO(coachDTO);
        
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
        RouteEntity route = new RouteEntity();
        route.setRouteId(trainDTO.getRouteId());
        trainEntity.setRoute(route);
        return trainEntity;
    }

}