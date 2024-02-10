package com.app.dao;

import com.app.dto.SeatAvailabilityDTO;
import com.app.entities.TrainEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainDAO extends JpaRepository<TrainEntity, Long> {

	@Query("SELECT t FROM TrainEntity t WHERE t.route.routeId = :routeId AND t.runsOn LIKE %:day%")
	List<TrainEntity> findByRouteIdAndRunsOnDay(Long routeId, String day);

//	 @Query(value = "SELECT new com.app.dto.CoachCountDTO(t.trainNumber, t.dateOfJourney, t.acCount, t.sleeperCount, t.generalCount) FROM train_journey_coach_summary t WHERE t.trainNumber = :trainNumber AND t.dateOfJourney = :dateOfJourney", nativeQuery = true)
//	    Optional<SeatAvailabilityDTO> findCoachCountsByTrainNumberAndDateOfJourney(Long trainNumber, LocalDate dateOfJourney);

//	
	//@Query(value = "SELECT new com.app.dto.CoachCountDTO(t.train_number, t.date_of_journey, t.ac_count, t.sleeper_count, t.general_count) "
//			+ "FROM train_journey_coach_summary t "
//			+ "WHERE t.train_number = :trainNumber AND t.date_of_journey = :dateOfJourney", nativeQuery = true)
	
	
//	@Query(value = "SELECT new com.app.dto.CoachCountDTO(t.train_number, t.date_of_journey, t.ac_count, t.sleeper_count, t.general_count) "
//			+ "FROM train_journey_coach_summary t "
//			+ "WHERE t.train_number = :trainNumber AND t.date_of_journey = :dateOfJourney", nativeQuery = true)
//	Optional<SeatAvailabilityDTO> findCoachCountsByTrainNumberAndDateOfJourney(@Param("trainNumber") Long trainNumber,
//			@Param("dateOfJourney") LocalDate dateOfJourney);
	
	@Query(value = "SELECT t.train_number, t.date_of_journey, t.ac_count, t.sleeper_count, t.general_count " +
            "FROM train_journey_coach_summary t " +
            "WHERE t.train_number = :trainNumber AND t.date_of_journey = :dateOfJourney", nativeQuery = true)
Optional<Object[]> findCoachCountsByTrainNumberAndDateOfJourney(@Param("trainNumber") Long trainNumber,
                                                             @Param("dateOfJourney") LocalDate dateOfJourney);

}
