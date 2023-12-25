package com.booking.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "customer_entity",schema = "booking")
public class CustomerEntity extends AuditEntity<String>{
	
	@Column(name = "entity_type")
	private String entityType;
	@Id
	@Column(name = "entity_code")
	private String entityCode;
	@Column(name = "entity_name")
	private String entityName;
	@Column(name = "entity_owner_id")
	private String entityOwnerId;
	@Column(name = "card_icon")
	private String cardIcon;
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "fk_entity_code",referencedColumnName = "entity_code" )
//	private Set<Booking> bookings;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_entity_code",referencedColumnName = "entity_code" )
	private Set<Questionnaire> questions;

}
