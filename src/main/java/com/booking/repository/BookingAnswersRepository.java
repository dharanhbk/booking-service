package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.BookingAnswers;

@Repository
public interface BookingAnswersRepository extends JpaRepository<BookingAnswers, Long> {

}
