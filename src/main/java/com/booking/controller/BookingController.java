package com.booking.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.request.BookingRequest;
import com.booking.model.response.BookingResponse;
import com.booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0")
@RequiredArgsConstructor
@Tag(name = "Booking", description = "Booking service APIs")
public class BookingController {

	private final BookingService bookingService;

	@Operation(summary = "Retrieve a Tutorial by Id", 
			description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.", 
			tags = {"Booking"})
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = BookingResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getBookingDetailsById/{bookingId}")
	public BookingResponse getBookingDetlbyId(@PathVariable Optional<Long> bookingId) {
		return bookingService.findByBookingId(bookingId);
	}

	@GetMapping("/getBookingDetailsByEntityCode/{entityCode}")
	public BookingResponse getBookingDetlsbyEntityCode(@PathVariable Optional<String> entityCode,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "fetchSize", defaultValue = "10") Integer fetchSize) {
		return bookingService.findAllBookingsByEntityCode(entityCode, pageNo, fetchSize);
	}

	@PostMapping("/saveBookingDetails")
	public BookingResponse saveBookingDetails(@RequestBody BookingRequest request) {
		Principal user = new Principal() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "gokul";
			}
		};
		return bookingService.saveBookingDetails(request);
	}

	@PutMapping("/updateBookingDetails")
	public BookingResponse updateBookingDetailsById(@RequestBody BookingRequest request) {
		return bookingService.updateBookingDetails(request);
	}
	
	@GetMapping("/getQuestionsByEntityCode/{entityCode}")
	public BookingResponse getQuestionsByEntityCode(@PathVariable Optional<String> entityCode) {
		return bookingService.getQuestionsByEntityCode(entityCode);
	}
}
