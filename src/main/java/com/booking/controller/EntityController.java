package com.booking.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.EntityDto;
import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;
import com.booking.model.response.QueAnsResponse;
import com.booking.service.EntityService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0")
@RequiredArgsConstructor
@Tag(name = "Entity", description = "Entity controller service APIs")
public class EntityController {
	
	public final EntityService entityService;
	
	@PostMapping("/saveEntityDetails")
	public ResponseEntity<EntityDto> saveEntityDetails(@RequestBody EntityRequest request, Principal principal){
		if(principal != null) {
			request.setEntityOwnerId(principal.getName());
			return new ResponseEntity<>(entityService.saveEntityDetails(request), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getEntityDetails/{entityCode}")
	public ResponseEntity<EntityDto> getEntityDetails(@PathVariable("entityCode") Optional<String> entityCode){
		return new ResponseEntity<>(entityService.getEntityDetails(entityCode), HttpStatus.OK);
	}
	
	@GetMapping("/getAllEntityCards")
	public ResponseEntity<EntityResponse> getAllEntityCards(Principal principal){
		return new ResponseEntity<>(entityService.getAllEntityCards(principal), HttpStatus.OK);
	}
	
	@GetMapping("/getQuestionsByEntityCode/{entityCode}")
	public QueAnsResponse getQuestionsByEntityCode(@PathVariable Optional<String> entityCode,
			@RequestParam(name = "questionCategory",required = false) Optional<String> questionCategory) {
		return entityService.getQuestionsByEntityCode(entityCode, questionCategory);
	}

}
