package com.booking.model.response;

import java.util.List;

import com.booking.model.QuestionnaireDto;

import lombok.Data;

@Data
public class EntityResponse {
	
	private String entityCode;
	private String entityName;
	private String entityType;
	private List<QuestionnaireDto> questions;

}
