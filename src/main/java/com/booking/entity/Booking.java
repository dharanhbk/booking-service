package com.booking.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "booking", schema = "booking")
public class Booking extends AuditEntity<String> implements Parent{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long bookingId;
	@Column(name = "requested_by")
	private String requestedBy;
	@Column(name = "requestor_type")
	private String requestorType;
	@Column(name = "approved_by")
	private String approvedBy;
	@Column(name = "approved_at")
	private Date approvedAt;
	@Column(name = "fk_entity_code", nullable = false)
	private String fkEntityCode;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_status_code")
	private Status fkStatus;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_booking_id",referencedColumnName = "booking_id" )
	private Set<BookingAnswers> answers;
	
	
	@Override
	public Long getId() {
		return this.getBookingId();
	}


	@Override
	public List<Answers> getPAnswers() {
		return this.answers.stream().map(ans -> (Answers) ans).toList();
	}
	
	
		

}
