package com.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "waiting_list")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "waiting_number"))
public class WaitingListEntity extends BaseEntity{
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id_fk", referencedColumnName = "ticket_id")
	private TicketEntity ticket;
	
	//we also want dateOfJourney,trainNumber & PNR but we want efficient table design
	//and the above 3 columns can be reached via the ticket_id
}
