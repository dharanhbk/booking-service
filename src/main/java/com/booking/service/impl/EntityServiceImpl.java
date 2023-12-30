package com.booking.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.booking.entity.CustomerEntity;
import com.booking.entity.Questionnaire;
import com.booking.model.EntityDto;
import com.booking.model.QuestionnaireDto;
import com.booking.model.mapper.EntityMapper;
import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;
import com.booking.model.response.QueAnsResponse;
import com.booking.repository.CustomerEntityRepository;
import com.booking.repository.QuestionnaireRepository;
import com.booking.service.EntityService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EntityServiceImpl implements EntityService {
	
	private final CustomerEntityRepository customerEntityRepository;
	
	private final QuestionnaireRepository questionnaireRepository;
	
	private EntityMapper entityMapper = EntityMapper.MAPPER;
	
	@Override
	public EntityDto saveEntityDetails(EntityRequest entityRequest) {
		String entityCode =entityRequest.getEntityCode();
		if(Objects.isNull(entityCode)) 
			entityCode = UUID.randomUUID().toString();
		entityRequest.setEntityCode(entityCode);
		entityRequest.getQuestions().forEach(q -> q.setFkEntityCode(entityRequest.getEntityCode()));
		CustomerEntity entity = entityMapper.mapToCustomerEntity(entityRequest);
		return entityMapper.mapToEntityResponse(customerEntityRepository.save(entity));
	}
	
	@Override
	public QuestionnaireDto saveEntityQuestion(QuestionnaireDto dto) {
		Questionnaire entity = entityMapper.mapToQuestionnaire(dto);
		return entityMapper.mapToQuestionnaireDto(questionnaireRepository.save(entity));
	}

	@Override
	public EntityDto getEntityDetails(Optional<String> entityCode) {
		if(entityCode.isPresent()) {
			Optional<CustomerEntity> cusEntity = customerEntityRepository.findById(entityCode.get());
			if(cusEntity.isPresent())
				return entityMapper.mapToEntityResponse(cusEntity.get());
		}
		return null;
	}
	
	@Override
	public QueAnsResponse getQuestionsByEntityCode(Optional<String> entityCode, Optional<String> quesCategory) {
		if(entityCode.isPresent() && quesCategory.isPresent())			
				return new QueAnsResponse(questionnaireRepository.findByFkEntityCodeAndQuestionCategory(
					entityCode.get(),quesCategory.get()), "Success", null);
		else if(entityCode.isPresent())			
			return new QueAnsResponse(questionnaireRepository.findByFkEntityCode(
				entityCode.get()), "Success", null);
		return new QueAnsResponse(null, "Invalid request!", null);
	}

	@Override
	public EntityResponse getAllEntityCards(Principal principal) {
		if(principal!=null) {
			List<CustomerEntity> entityLst = customerEntityRepository.findByEntityOwnerId(principal.getName());
			if(!CollectionUtils.isEmpty(entityLst)) {
				List<EntityDto> entityDto = entityLst.stream().map(en -> {
					en.setQuestions(null);
					return entityMapper.mapToEntityResponse(en);
					}).toList();
				return new EntityResponse(entityDto, "Success", null);
			}
		}
		return new EntityResponse(null, "Invalid request!", null);
	}
	
	

}
