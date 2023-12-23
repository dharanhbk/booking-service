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
//@RequiredArgsConstructor
@Tag(name = "Driver", description = "Driver service APIs")
public class DriverController {

	@Autowired
	@Qualifier("driver")
	private QueAnsService driverService;

	@Operation(summary = "Retrieve a Tutorial by Id", 
			description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.", 
			tags = {"Driver"})
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = QueAnsResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getDriverDetailsById/{driverId}")
	public QueAnsResponse getDriverDetlbyId(@PathVariable Optional<Long> driverId) {
		return driverService.findById(driverId);
	}

	@GetMapping("/getDriverDetailsByEntityCode/{entityCode}")
	public QueAnsResponse getDriverDetlsbyEntityCode(@PathVariable Optional<String> entityCode,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "fetchSize", defaultValue = "10") Integer fetchSize) {
		return driverService.findAllByEntityCode(entityCode, pageNo, fetchSize);
	}

	@PostMapping("/saveDriverDetails")
	public QueAnsResponse saveDriverDetails(@RequestBody QueAnsRequest request) {
		return driverService.save(request);
	}

	@PutMapping("/updateDriverDetails")
	public QueAnsResponse updateDriverDetailsById(@RequestBody QueAnsRequest request) {
		return driverService.update(request);
	}
	
}
