package com.booking.model.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable{
	
	private static final long serialVersionUID = 122697179863919193L;
	private HttpStatus status;
	private String message;
	

}
