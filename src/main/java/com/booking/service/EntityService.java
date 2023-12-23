package com.booking.service;


import java.util.Optional;

import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;
import com.booking.model.response.QueAnsResponse;

public interface EntityService {

	EntityResponse saveEntityDetails(EntityRequest entityRequest);

	EntityResponse getEntityDetails(Optional<String> entityCode);

	QueAnsResponse getQuestionsByEntityCode(Optional<String> entityCode, Optional<String> quesCategory);

}
