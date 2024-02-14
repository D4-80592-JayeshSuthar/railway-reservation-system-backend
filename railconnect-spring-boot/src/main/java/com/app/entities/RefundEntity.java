package com.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "refunds")
@AttributeOverride(name = "id", column = @Column(name = "refund_number"))
public class RefundEntity extends BaseEntity{

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id_fk", referencedColumnName = "ticket_id")
	private TicketEntity ticket;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RefundReasons reason;
	
	private Boolean refundStatus;
	
	@Column(nullable = false)
	private Double amount;
}
