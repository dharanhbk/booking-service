package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="answers",schema="booking")
public class Answers{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id",nullable=false)
	private Long answerId;
	@Column(name="question_code")
	private String questionCode;
	@Column(name="answer")
	private String answer;
	@Column(name="fk_booking_id")
	private Long bookingId;

}
