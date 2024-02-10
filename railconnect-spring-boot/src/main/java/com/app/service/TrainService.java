package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RouteDAO;
import com.app.dao.TrainDAO;
import com.app.dto.AddTrainDTO;
import com.app.dto.SearchTrainDTO;
import com.app.dto.SeatAvailabilityDTO;
import com.app.entities.RouteEntity;
import com.app.entities.TrainEntity;
import com.app.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class TrainService {

	@Autowired
	private TrainDAO trainDAO;

	@Autowired
	private RouteService routeService;

	@Autowired
	private RouteDAO routeDao;

	
	@Autowired
    private ModelMapper modelMapper; // Autowire ModelMapper
	
	public List<AddTrainDTO> getAllTrains() {
		return trainDAO.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public AddTrainDTO getTrainById(Long trainNumber) {
		TrainEntity trainEntity = trainDAO.findById((long) trainNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Train not found"));
		return convertToDTO(trainEntity);
	}

	public AddTrainDTO addTrain(AddTrainDTO trainDTO) {
		TrainEntity trainEntity = convertToEntity(trainDTO);
//        trainEntity.setRoute(routeService.getRouteById(trainDTO.getRouteId()).toEntity());
		trainEntity = trainDAO.save(trainEntity);
		return convertToDTO(trainEntity);
	}

	public void cancelTrain(Long trainNumber) {
		TrainEntity trainEntity = trainDAO.findById((long) trainNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Train not found"));
		trainEntity.setCancelStatus(true);
		trainEntity.setActiveStatus(false);
		trainDAO.save(trainEntity);
	}

	public void removeTrain(Long trainNumber) {
		TrainEntity trainEntity = trainDAO.findById((long) trainNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Train not found"));
		trainEntity.setCancelStatus(true);
		trainEntity.setActiveStatus(false);
		trainDAO.save(trainEntity);
	}

	// Service method to cancel a train and reschedule it to a particular date
//    public void cancelTrainAndReschedule(Long trainNumber, LocalDate rescheduleDate) {
//        TrainEntity trainEntity = trainDAO.findById(trainNumber)
//            .orElseThrow(() -> new ResourceNotFoundException("Train not found"));
//        
//        // Set cancelStatus and activeStatus
//        trainEntity.setCancelStatus(true);
//        trainEntity.setActiveStatus(false);
//        
//        // Reschedule the train to the provided date
//        trainEntity.setRunningDate(rescheduleDate);
//        
//        // Save the updated trainEntity
//        trainDAO.save(trainEntity);
//    }

//    public void scheduleTrainAfterJourneyCompletion(Long trainNumber, String runsOn) {
	// Logic to calculate the next running date based on runsOn and current date
//        LocalDate nextRunningDate = calculateNextRunningDate(runsOn);
//        
//        TrainEntity trainEntity = trainDAO.findById(trainNumber)
//            .orElseThrow(() -> new ResourceNotFoundException("Train not found"));
//        
//        // Update the running date of the train
//        trainEntity.setRunningDate(nextRunningDate);
//        
//        // Set cancelStatus to false and activeStatus to true
//        trainEntity.setCancelStatus(false);
//        trainEntity.setActiveStatus(true);
//        
//        // Save the updated trainEntity
//        trainDAO.save(trainEntity);
//    }
//    
//    private LocalDate calculateNextRunningDate(String runsOn) {
	// Logic to calculate the next running date based on runsOn and current date
	// Implement your logic here
//        return LocalDate.now().plusDays(7); // Example: Adding 7 days for weekly scheduling
//    }

	// Method to toggle the status of a train when it starts running
//    public void toggleTrainStatusWhenRunning() {
	// Get all trains from the database
//        List<TrainEntity> allTrains = trainDAO.findAll();
//
//        // Iterate over each train
//        for (TrainEntity train : allTrains) {
//            // Check if the train is canceled and inactive
//            if (train.isCancelStatus() && !train.isActiveStatus()) {
//                // Check if the current date matches the scheduled running date
//                if (LocalDate.now().isEqual(train.getRunningDate())) {
//                    // Toggle the cancelStatus to false and activeStatus to true
//                    train.setCancelStatus(false);
//                    train.setActiveStatus(true);
//
//                    // Save the updated train entity
//                    trainDAO.save(train);
//                }
//            }
//        }
//    }

	private AddTrainDTO convertToDTO(TrainEntity trainEntity) {
		AddTrainDTO trainDTO = new AddTrainDTO();
		trainDTO.setTrainNumber(trainEntity.getTrainNumber());
		trainDTO.setTrainName(trainEntity.getTrainName());
		trainDTO.setArrivalTime(trainEntity.getArrivalTime());
		trainDTO.setDepartureTime(trainEntity.getDepartureTime());
//        trainDTO.setRunningDate(trainEntity.getRunningDate());
		trainDTO.setBaseFare(trainEntity.getBaseFare());
		trainDTO.setActiveStatus(trainEntity.isActiveStatus());
		trainDTO.setCancelStatus(trainEntity.isCancelStatus());
		trainDTO.setRouteId(trainEntity.getRoute().getRouteId());
		trainDTO.setRunsOn(trainEntity.getRunsOn());
		trainDTO.setAcSeats(trainEntity.getAcSeats());
		trainDTO.setSleeperSeats(trainEntity.getSleeperSeats());
		trainDTO.setGeneralSeats(trainEntity.getGeneralSeats());
//        trainDTO.setScheduleLink(trainEntity.getScheduleLink());
//        trainDTO.setDepartureStation(trainEntity.getDepartureStation());
//        trainDTO.setArrivalStation(trainEntity.getArrivalStation());
//        trainDTO.setDuration(trainEntity.getDuration());

		// Set the coachDTO
//        if (trainEntity.getCoach() != null) {
//            CoachDTO coachDTO = new CoachDTO();
//            coachDTO.setCoachType(trainEntity.getCoach().getCoachType().toString());
//            trainDTO.setCoachDTO(coachDTO);
//        }

		return trainDTO;
	}

	private TrainEntity convertToEntity(AddTrainDTO trainDTO) {
		TrainEntity trainEntity = new TrainEntity();
//        trainEntity.setTrainNumber(trainDTO.getTrainNumber());
		trainEntity.setTrainName(trainDTO.getTrainName());
		trainEntity.setArrivalTime(trainDTO.getArrivalTime());
		trainEntity.setDepartureTime(trainDTO.getDepartureTime());
//        trainEntity.setRunningDate(trainDTO.getRunningDate());
		trainEntity.setBaseFare(trainDTO.getBaseFare());
		trainEntity.setActiveStatus(trainDTO.isActiveStatus());
		trainEntity.setCancelStatus(trainDTO.isCancelStatus());
		trainEntity.setRunsOn(trainDTO.getRunsOn());
		trainEntity.setRoute(routeDao.findById(trainDTO.getRouteId())
				.orElseThrow(() -> new ResourceNotFoundException("Route Not Found")));
		trainEntity.setAcSeats(trainDTO.getAcSeats());
		trainEntity.setSleeperSeats(trainDTO.getSleeperSeats());
		trainEntity.setGeneralSeats(trainDTO.getGeneralSeats());
//        trainEntity.setScheduleLink(trainDTO.getScheduleLink());
//        trainEntity.setDepartureStation(trainDTO.getDepartureStation());
//        trainEntity.setArrivalStation(trainDTO.getArrivalStation());
//        trainEntity.setDuration(trainDTO.getDuration());

		// Set the coach
//        if (trainDTO.getCoachDTO() != null && trainDTO.getCoachDTO().getCoachType() != null) {
//            trainEntity.setCoachType(Coaches.valueOf(trainDTO.getCoachDTO().getCoachType()));
//        }

		return trainEntity;
	}

  public List<SearchTrainDTO> getTrainsBySrcDescDate(String src, String des, LocalDate dateOfJourney) {
        String day = dateOfJourney.getDayOfWeek().toString().substring(0, 3);

        RouteEntity route = routeDao.findBySourceAndDestination(src, des)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        List<TrainEntity> trainEntities = trainDAO.findByRouteIdAndRunsOnDay(route.getRouteId(), day);

        List<SearchTrainDTO> searchTrains = new ArrayList<>();

        if (!trainEntities.isEmpty()) {
            for (TrainEntity trainEntity : trainEntities) {
                SearchTrainDTO searchTrain = modelMapper.map(trainEntity, SearchTrainDTO.class); // Utilize ModelMapper
                searchTrain.setSource(src);
                searchTrain.setDestination(des);
                searchTrain.setDateOfJourney(dateOfJourney.toString());

                Optional<Object[]> seatsOptional = trainDAO
                        .findCoachCountsByTrainNumberAndDateOfJourney(trainEntity.getTrainNumber(), dateOfJourney);

                if (seatsOptional.isPresent()) {
                    Object[] seats = seatsOptional.get();
                    if (seats.length > 0) {
                        SeatAvailabilityDTO seatAvailabilityDTO = new SeatAvailabilityDTO();
                        seatAvailabilityDTO.setTrainNumber((Long) seats[0]);
                        seatAvailabilityDTO.setDateOfJourney((LocalDate) seats[1]);
                        seatAvailabilityDTO.setAcCount((Integer) seats[2]);
                        seatAvailabilityDTO.setSleeperCount((Integer) seats[3]);
                        seatAvailabilityDTO.setGeneralCount((Integer) seats[4]);

                        searchTrain.setAcSeats(trainEntity.getAcSeats() - seatAvailabilityDTO.getAcCount());
                        searchTrain.setSleeperSeats(trainEntity.getSleeperSeats() - seatAvailabilityDTO.getSleeperCount());
                        searchTrain.setGeneralSeats(trainEntity.getGeneralSeats() - seatAvailabilityDTO.getGeneralCount());
                    } else {
                        // Handle no seat availability data
                    }
                } else {
                    // Handle no seat availability data
                }

                searchTrains.add(searchTrain);
            }
        } else {
            // Handle no trains found
        }

        return searchTrains;
    }

}
