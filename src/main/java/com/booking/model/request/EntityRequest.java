package com.booking.model.request;

import java.util.List;

import com.booking.model.QuestionnaireDto;

import lombok.Data;

@Data
public class EntityRequest {
	
	private String entityCode;
	private String entityName;
	private String entityType;
	private List<QuestionnaireDto> questions;

}
