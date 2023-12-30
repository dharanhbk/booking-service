package com.booking.model;

import java.util.List;

import com.booking.markerinterface.BasicInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionnaireAnswer {

	@NotBlank(groups = BasicInfo.class)
	private String questionCode;
	private String parentQuestionCode;
	@NotNull(groups = BasicInfo.class)
	private Integer questionVersion;
	private String questionCategory;
	private String questionText;
	private Long answerId;
	@NotBlank(groups = BasicInfo.class)
	private String answer;
	private List<QuestionnaireAnswer> subQuesAnswers;
	
}
