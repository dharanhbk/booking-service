package com.booking.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public record QueAnsResponse (
		Object data,
		String message,
		@JsonInclude(value = Include.NON_NULL) Integer totalCount
		){}
