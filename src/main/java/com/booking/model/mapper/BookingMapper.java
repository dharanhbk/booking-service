package com.booking.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.booking.entity.Booking;
import com.booking.model.response.BookingResponse;

@Mapper
public interface BookingMapper {

	BookingMapper MAPPER = Mappers.getMapper(BookingMapper.class);

//    @Mapping(source = "email", target = "emailAddress")
//    BookingResponse mapToUserDto(Booking user);
}
