package com.booking.model;

import lombok.Data;

@Data
public class QuestionnaireDto {
	
	private String questionId;
	private String questionText;
	private String questionDesc;
	private String possibleAnswers;
	private String questionType;
	private String questionDataType;
	private Boolean isMainMandatory;
	private String questionCode;
	private String parentQuestionCode;
	private String fkEntityCode;
	private Integer questionVersion;
	private String questionCategory;
	private boolean isActiveInd;
	private Integer columnOrderId;

}
