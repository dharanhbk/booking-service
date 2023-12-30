package com.booking.model;

import lombok.Data;

@Data
public class AnswerDto {

	
	private Long pId;
	private Long answerId;
	private String questionCode;
	private String answer;
	private Long bookingId;
}
