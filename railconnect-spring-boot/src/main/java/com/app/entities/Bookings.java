package com.app.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr")
    @SequenceGenerator(name = "pnr", initialValue = 23342783)
    @Column(name = "pnr_number")
    private Long pnrNumber;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tickets> tickets = new HashSet<>();

    @Column(name = "coach_type", length = 20)
    private CoachEntity coach;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "train_number")
    private int trainNumber;

    @Column(name = "source", length = 20)
    private String source;

    @Column(name = "destination", length = 20)
    private String destination;

    @Column(name = "date_of_journey")
    private LocalDate dateOfJourney;
}
