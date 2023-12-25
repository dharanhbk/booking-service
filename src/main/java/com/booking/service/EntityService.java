package com.booking.service;


import java.security.Principal;
import java.util.Optional;

import com.booking.model.EntityDto;
import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;
import com.booking.model.response.QueAnsResponse;

public interface EntityService {

	EntityDto saveEntityDetails(EntityRequest entityRequest);

	EntityDto getEntityDetails(Optional<String> entityCode);

	QueAnsResponse getQuestionsByEntityCode(Optional<String> entityCode, Optional<String> quesCategory);

	EntityResponse getAllEntityCards(Principal principal);

}
