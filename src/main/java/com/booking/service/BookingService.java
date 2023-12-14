package com.booking.service;

import java.util.Optional;

import com.booking.model.request.BookingRequest;
import com.booking.model.response.BookingResponse;

public interface BookingService {

	BookingResponse findByBookingId(Optional<Long> bookingId);

	BookingResponse findAllBookingsByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize);

	BookingResponse saveBookingDetails(BookingRequest request);

	BookingResponse updateBookingDetails(BookingRequest request);

}
