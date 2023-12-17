package com.booking.service;


import java.util.Optional;

import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;

public interface EntityService {

	EntityResponse saveEntityDetails(EntityRequest entityRequest);

	EntityResponse getEntityDetails(Optional<String> entityCode);

}
