package com.booking.model.request;

import java.util.List;

import com.booking.model.QuestionnaireAnswer;

import lombok.Data;

@Data
public class BookingRequest {
	
	private String fkEntityCode;
	private Long bookingId;
	private List<QuestionnaireAnswer> quesAnswers;

}
