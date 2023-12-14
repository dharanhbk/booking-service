package com.booking.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends Exception{

	
	private static final long serialVersionUID = 2012253888826329392L;
	private final String message;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	private final Throwable cause;
	
	public CustomException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, Throwable cause) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.cause = cause;
	}
	
	public CustomException(String message,Throwable cause) {
		super();
		this.message = message;
		this.httpStatus = null;
		this.timestamp = null;
		this.cause = cause;
	}
	
	public CustomException(String message) {
		super();
		this.message = message;
		this.httpStatus = null;
		this.timestamp = null;
		this.cause = null;
	}
	
	

}
