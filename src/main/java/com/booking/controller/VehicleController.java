package com.booking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.request.QueAnsRequest;
import com.booking.model.response.QueAnsResponse;
import com.booking.service.QueAnsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1.0")
@Tag(name = "Vehicle", description = "Vehicle service APIs")
public class VehicleController {

	@Autowired
	@Qualifier("vehicle")
	private QueAnsService vehicleService;

	@Operation(summary = "Retrieve a Tutorial by Id", 
			description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.", 
			tags = {"Vehicle"})
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = QueAnsResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getVehicleDetailsById/{vehicleId}")
	public QueAnsResponse getVehicleDetlbyId(@PathVariable Optional<Long> vehicleId) {
		return vehicleService.findById(vehicleId);
	}

	@GetMapping("/getVehicleDetailsByEntityCode/{entityCode}")
	public QueAnsResponse getVehicleDetlsbyEntityCode(@PathVariable Optional<String> entityCode,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "fetchSize", defaultValue = "10") Integer fetchSize) {
		return vehicleService.findAllByEntityCode(entityCode, pageNo, fetchSize);
	}

	@PostMapping("/saveVehicleDetails")
	public QueAnsResponse saveVehicleDetails(@RequestBody QueAnsRequest request) {
		return vehicleService.save(request);
	}

	@PutMapping("/updateVehicleDetails")
	public QueAnsResponse updateVehicleDetailsById(@RequestBody QueAnsRequest request) {
		return vehicleService.update(request);
	}
	
	
}
