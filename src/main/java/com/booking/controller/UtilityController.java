package com.booking.controller;

import java.util.Base64;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.response.UtilityResponse;
import com.booking.utils.UtilityConstants;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/utility/v1.0")
@Tag(name = "Vehicle", description = "Vehicle service APIs")
public class UtilityController {
	
	@GetMapping("/calculate/{formula}")
	public ResponseEntity<UtilityResponse> calulateFormula(@PathVariable("formula") String formula,
			@RequestParam Map<String,String> params ){
		Objects.requireNonNull(formula);
		formula = new String(Base64.getDecoder().decode(formula.getBytes()));
		return new ResponseEntity<>(new UtilityResponse(UtilityConstants.evaluateFormula(formula, params)),HttpStatus.OK);
	}

}
