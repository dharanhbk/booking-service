package com.booking.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookingException extends CustomException{

	
	private static final long serialVersionUID = 1821712696905586121L;
	
	public BookingException(String message, HttpStatus httpStatus, ZonedDateTime timestamp,Throwable throwable) {
		super(message,httpStatus,timestamp, throwable);
	}
	
	public BookingException(String message) {
		super(message);
	}
	
	public BookingException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	

}
