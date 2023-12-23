package com.booking.entity;

import java.util.Date;
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
@Table(name = "vehicle", schema = "booking")
public class Vehicle extends AuditEntity<String>{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private Long vehicleId;
	@Column(name = "fk_entity_code", nullable = false)
	private String fkEntityCode;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_status_code")
	private Status fkStatus;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_vehicle_id",referencedColumnName = "vehicle_id" )
	private Set<VehicleAnswers> answers;
	
	
		

}
