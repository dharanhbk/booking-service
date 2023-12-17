package com.booking.model.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import com.booking.entity.CustomerEntity;
import com.booking.entity.Questionnaire;
import com.booking.model.QuestionnaireDto;
import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;

//@Configuration
@Mapper
public interface EntityMapper {
	
	EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

	CustomerEntity mapToCustomerEntity(EntityRequest entityRequest);
	
	Questionnaire mapToQuestionnaire(QuestionnaireDto dto);
	
	QuestionnaireDto mapToQuestionnaireDto(Questionnaire q);

	EntityResponse mapToEntityResponse(CustomerEntity ce);
	
	

}
