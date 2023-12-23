package com.booking.model.request;

import java.util.List;

import com.booking.markerinterface.AdvanceInfo;
import com.booking.markerinterface.BasicInfo;
import com.booking.model.QuestionnaireAnswer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QueAnsRequest {
	
	@NotBlank(groups = BasicInfo.class)
	private String fkEntityCode;
	@NotBlank(groups = AdvanceInfo.class)
	private Long id;
	private List<QuestionnaireAnswer> quesAnswers;

}
