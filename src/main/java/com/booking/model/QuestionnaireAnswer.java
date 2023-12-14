package com.booking.model;

import java.util.List;

import lombok.Data;

@Data
public class QuestionnaireAnswer {

	
	private String questionCode;
	private String parentQuestionCode;
	private Integer questionVersion;
	private String questionCategory;
	private Long answerId;
	private String answer;
	private List<QuestionnaireAnswer> subQuesAnswers;
	
}
