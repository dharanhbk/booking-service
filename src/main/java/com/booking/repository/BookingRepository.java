package com.booking.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.entity.Booking;

import jakarta.persistence.Tuple;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	Page<Booking> findByFkEntityCode(String string, Pageable page);

	@Query(value = "select q.question_text,ba.answer,q.question_code,b.fk_entity_code  "
			+ "from booking.booking b inner join booking.booking_answers ba on b.booking_id =ba.fk_booking_id "
			+ "inner join booking.questionnaire q on b.fk_entity_code =q.fk_entity_code and ba.question_code =q.question_code "
			+ "where b.booking_id =:bookingId and q.question_category = :category",
			nativeQuery = true)
	List<Tuple> findByBookingIdAndCategory(@Param(value = "bookingId") Long bookingId,@Param(value = "category") String category);

}
