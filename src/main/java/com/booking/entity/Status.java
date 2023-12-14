package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "status",schema = "booking")
public class Status {
	
	@Id
	@Column(name = "status_code")
	private String statusCode;
	@Column(name = "status")
	private String status;
	@Column(name = "status_desc")
	private String statusDesc;

}
