package com.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.booking.entity.Answers;
import com.booking.entity.Booking;
import com.booking.entity.CustomerEntity;
import com.booking.entity.Questionnaire;
import com.booking.model.request.BookingRequest;
import com.booking.model.response.BookingResponse;
import com.booking.repository.BookingRepository;
import com.booking.repository.CustomerEntityRepository;
import com.booking.repository.QuestionnaireRepository;
import com.booking.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
	
	private final BookingRepository bookingRepository;
	
	private final CustomerEntityRepository customerEntityRepository;
	
	private final QuestionnaireRepository questionnaireRepository;
	
	@Override
	public BookingResponse findByBookingId(Optional<Long> bookingId) {
		if(bookingId.isPresent()) 
			return new BookingResponse(bookingRepository.findById(bookingId.get()),"success",null);
		return new BookingResponse(null, "Invalid Request",null);
	}
	
	@Override
	public BookingResponse findAllBookingsByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize) {
		if(entityCode.isPresent()) {
			Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(entityCode.get());
			Pageable page = PageRequest.of(pageNo, fetchSize, Sort.by("bookingId").descending());
			Page<Booking> paginatedResult = bookingRepository.findByFkEntityCode(entityCode.get(),page);
			if(paginatedResult.hasContent() && customerEntity.isPresent()) {
				List<Booking> bookings = paginatedResult.toList();
				Set<Questionnaire> questions = customerEntity.get().getQuestions();
				String[][] bookingGrid = new String[bookings.size()+1][questions.size()+1];
				List<Questionnaire> questionsSorted = questions.stream()
						.sorted((o1,o2)->o1.getColumnOrderId().compareTo(o2.getColumnOrderId())).toList();
				List<String> header = new ArrayList<>();
				header.add("Booking Id");
				bookingGrid[0][0] = "Booking Id";
				for(int i=0;i<questionsSorted.size();i++) {
					header.add(questionsSorted.get(i).getQuestionCode());
					bookingGrid[0][i+1] = questionsSorted.get(i).getQuestionText();
				}
				int row =1;
				for(Booking booking:bookings) {
					bookingGrid[row][0] = booking.getBookingId().toString();
					for(Answers ans:booking.getAnswers()) {
						int col = header.indexOf(ans.getQuestionCode());
						bookingGrid[row][col] = ans.getAnswer();
					}
					row++;
				}
				return new BookingResponse(bookingGrid, "success",paginatedResult.getNumberOfElements());
			}
		}
		return new BookingResponse(null, "Invalid Request",null);
		
	}

	@Override
	public BookingResponse saveBookingDetails(BookingRequest request) {
		Booking booking = new Booking();
		booking.setFkEntityCode(request.getFkEntityCode());
		booking = bookingRepository.save(booking);
		final Long bookingId = booking.getBookingId();
		Set<Answers> answer = request.getQuesAnswers().stream().map(ans->{
			Answers en = new Answers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setBookingId(bookingId);
			return en;
		}).collect(Collectors.toSet());
		booking.setAnswers(answer);
		booking = bookingRepository.save(booking);
		return new BookingResponse(booking, "success", null);
	}

	@Override
	public BookingResponse updateBookingDetails(BookingRequest request) {
		Booking booking = new Booking();
		booking.setFkEntityCode(request.getFkEntityCode());
		booking.setBookingId(request.getBookingId());
		Set<Answers> answer = request.getQuesAnswers().stream().map(ans->{
			Answers en = new Answers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setAnswerId(ans.getAnswerId());
			return en;
		}).collect(Collectors.toSet());
		booking.setAnswers(answer);
		booking = bookingRepository.save(booking);
		return new BookingResponse(booking, "success", null);
	}

	@Override
	public BookingResponse getQuestionsByEntityCode(Optional<String> entityCode) {
		if(entityCode.isPresent())
			return new BookingResponse(questionnaireRepository.findByFkEntityCode(entityCode.get()), "Success", null);
		return new BookingResponse(null, "Invalid request!", null);
	}
	

}
