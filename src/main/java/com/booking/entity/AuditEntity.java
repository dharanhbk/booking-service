package com.booking.entity;

import java.util.Date;

import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@EntityListeners(AuditListener.class)
public class AuditEntity<T> {
	
	@Column(name="created_by")
	@CreatedBy
	private T createdBy;
	@Column(name="created_at")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name="updated_by")
	@LastModifiedBy
	private T updatedBy;
	@Column(name="updated_at")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

}
