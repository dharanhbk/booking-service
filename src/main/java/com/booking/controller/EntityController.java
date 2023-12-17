package com.booking.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.request.EntityRequest;
import com.booking.model.response.EntityResponse;
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
	public ResponseEntity<EntityResponse> saveEntityDetails(@RequestBody EntityRequest request){
		return new ResponseEntity<>(entityService.saveEntityDetails(request), HttpStatus.CREATED);
	}
	
	@GetMapping("/getEntityDetails/{entityCode}")
	public ResponseEntity<EntityResponse> getEntityDetails(@PathVariable("entityCode") Optional<String> entityCode){
		return new ResponseEntity<>(entityService.getEntityDetails(entityCode), HttpStatus.OK);
	}

}
