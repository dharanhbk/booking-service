package com.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.booking.entity.BookingAnswers;
import com.booking.entity.Booking;
import com.booking.entity.CustomerEntity;
import com.booking.entity.Questionnaire;
import com.booking.model.request.QueAnsRequest;
import com.booking.model.response.QueAnsResponse;
import com.booking.repository.BookingRepository;
import com.booking.repository.CustomerEntityRepository;
import com.booking.service.QueAnsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("booking")
public class BookingServiceImpl implements QueAnsService {
	
	private final BookingRepository bookingRepository;
	
	private final CustomerEntityRepository customerEntityRepository;
	
	public static final String CATEGORY = "BOOKING";
	
	@Override
	public QueAnsResponse findById(Optional<Long> bookingId) {
		if(bookingId.isPresent()) 
			return new QueAnsResponse(bookingRepository.findById(bookingId.get()),"success",null);
		return new QueAnsResponse(null, "Invalid Request",null);
	}
	
	@Override
	public QueAnsResponse findAllByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize) {
		if(entityCode.isPresent()) {
			Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(entityCode.get());
			Pageable page = PageRequest.of(pageNo, fetchSize, Sort.by("bookingId").descending());
			Page<Booking> paginatedResult = bookingRepository.findByFkEntityCode(entityCode.get(),page);
			if(paginatedResult.hasContent() && customerEntity.isPresent()) {
				List<Booking> bookings = paginatedResult.toList();
				List<Questionnaire> questions = customerEntity.get().getQuestions().stream().filter(en -> 
				(Objects.nonNull(en.getQuestionCategory()) && en.getQuestionCategory().equalsIgnoreCase(CATEGORY))).toList();
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
					for(BookingAnswers ans:booking.getAnswers()) {
						int col = header.indexOf(ans.getQuestionCode());
						if(col>=0)
							bookingGrid[row][col] = ans.getAnswer();
					}
					row++;
				}
				return new QueAnsResponse(bookingGrid, "success",paginatedResult.getNumberOfElements());
			}
		}
		return new QueAnsResponse(null, "Invalid Request",null);
		
	}

	@Override
	public QueAnsResponse save(QueAnsRequest request) {
		Booking booking = new Booking();
		booking.setFkEntityCode(request.getFkEntityCode());
		booking = bookingRepository.save(booking);
		final Long bookingId = booking.getBookingId();
		Set<BookingAnswers> answer = request.getQuesAnswers().stream().map(ans->{
			BookingAnswers en = new BookingAnswers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setBookingId(bookingId);
			return en;
		}).collect(Collectors.toSet());
		booking.setAnswers(answer);
		booking = bookingRepository.save(booking);
		return new QueAnsResponse(booking, "success", null);
	}

	@Override
	public QueAnsResponse update(QueAnsRequest request) {
		Booking booking = new Booking();
		booking.setFkEntityCode(request.getFkEntityCode());
		booking.setBookingId(request.getId());
		Set<BookingAnswers> answer = request.getQuesAnswers().stream().map(ans->{
			BookingAnswers en = new BookingAnswers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setAnswerId(ans.getAnswerId());
			return en;
		}).collect(Collectors.toSet());
		booking.setAnswers(answer);
		booking = bookingRepository.save(booking);
		return new QueAnsResponse(booking, "success", null);
	}


	@Override
	public QueAnsResponse delete(QueAnsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
