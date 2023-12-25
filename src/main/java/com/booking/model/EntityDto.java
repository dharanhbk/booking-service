package com.booking.model;

import java.util.List;

import com.booking.model.QuestionnaireDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class EntityDto {
	
	private String entityCode;
	private String entityName;
	private String entityType;
//	@JsonInclude(value = Include.NON_NULL)
	private String entityOwnerId;
	private String cardIcon;
	@JsonInclude(value = Include.NON_EMPTY)
	private List<QuestionnaireDto> questions;

}
