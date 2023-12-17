package com.booking.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity<T> {
	
	@Column(name="created_by",updatable = false)
	@CreatedBy
	@JsonIgnore
	protected T createdBy;
	@Column(name="created_at",updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	protected Date createdAt;
	@Column(name="updated_by")
	@LastModifiedBy
	@JsonIgnore
	protected T updatedBy;
	@Column(name="updated_at")
	@LastModifiedDate
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedAt;

}
