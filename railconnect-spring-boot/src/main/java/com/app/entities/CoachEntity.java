package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "coachCapacity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CoachEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coach_id")
	private Long coachId;

//	@OneToOne(fetch = FetchType.LAZY)
//	@MapsId
//	@JoinColumn(name = "train_number")
//	private TrainEntity train;

	@Column(name = "first_class")
	private int firstClass;

//    @OneToOne(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
//    private BookingEntity booking;

	@Column(name = "second_class")
	private int secondClass;

	@Column(name = "sleeper")
	private int sleeper;

	@Column(name = "third_ac")
	private int thirdAC;

	@Column(name = "chair_car")
	private int chairCar;

//	public Coaches getCoachType() {
//		if (firstClass > 0) {
//			return Coaches.FIRSTCLASS;
//		} else if (secondClass > 0) {
//			return Coaches.SECONDCLASS;
//		} else if (sleeper > 0) {
//			return Coaches.SLEEPER;
//		} else if (thirdAC > 0) {
//			return Coaches.THIRDAC;
//		} else if (chairCar > 0) {
//			return Coaches.CHAIRCAR;
//		} else {
//			return null;
//		}
//}

}
