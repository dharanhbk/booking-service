package com.booking.service;

import java.util.Optional;

import com.booking.model.request.QueAnsRequest;
import com.booking.model.response.QueAnsResponse;

public interface QueAnsService {

	QueAnsResponse findById(Optional<Long> id);

	QueAnsResponse findAllByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize);

	QueAnsResponse save(QueAnsRequest request);

	QueAnsResponse update(QueAnsRequest request);
	
	QueAnsResponse delete(QueAnsRequest request);

}
