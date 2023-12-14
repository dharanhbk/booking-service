package com.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booking.model.BaseResponse;

@ControllerAdvice
public class BookingExceptionHandler extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> customException(Exception ex){
		HttpStatus st = HttpStatus.INTERNAL_SERVER_ERROR;
		if(ex.getCause() instanceof BookingException bExp) 
			return new ResponseEntity<>(new BaseResponse(bExp.getHttpStatus(),bExp.getMessage()),bExp.getHttpStatus());
		else 
			return new ResponseEntity<>(new BaseResponse(st, getLocalizedMessage()),st);
	}
	
	
	
	

}
