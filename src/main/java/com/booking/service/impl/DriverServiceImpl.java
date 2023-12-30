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
import org.springframework.util.CollectionUtils;

import com.booking.entity.DriverAnswers;
import com.booking.entity.Parent;
import com.booking.entity.Driver;
import com.booking.entity.CustomerEntity;
import com.booking.entity.Questionnaire;
import com.booking.model.request.QueAnsRequest;
import com.booking.model.response.QueAnsResponse;
import com.booking.repository.DriverRepository;
import com.booking.repository.CustomerEntityRepository;
import com.booking.service.QueAnsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("driver")
public class DriverServiceImpl implements QueAnsService {
	
	private final DriverRepository driverRepository;
	
	private final CustomerEntityRepository customerEntityRepository;
	
	private static final String CATEGORY = "DRIVER";
	
	@Override
	public QueAnsResponse findById(Optional<Long> driverId) {
		if(driverId.isPresent()) 
			return new QueAnsResponse(driverRepository.findById(driverId.get()),"success",null);
		return new QueAnsResponse(null, "Invalid Request",null);
	}
	
	@Override
	public QueAnsResponse findAllByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize) {
		if(entityCode.isPresent()) {
			Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(entityCode.get());
			Pageable page = PageRequest.of(pageNo, fetchSize, Sort.by("driverId").descending());
			Page<Driver> paginatedResult = driverRepository.findByFkEntityCode(entityCode.get(),page);
			if(paginatedResult.hasContent() && customerEntity.isPresent()) {
				return QueAnsService.super.mapParentToGrid(customerEntity.get().getQuestions(), 
						paginatedResult.toList().stream().map(b -> (Parent) b).toList(), CATEGORY, paginatedResult.getTotalElements());
			}
		}
		return new QueAnsResponse(null, "Invalid Request",null);
		
	}

	@Override
	public QueAnsResponse save(QueAnsRequest request) {
		Driver driver = new Driver();
		driver.setFkEntityCode(request.getFkEntityCode());
		driver = driverRepository.save(driver);
		final Long driverId = driver.getDriverId();
		Set<DriverAnswers> answer = request.getQuesAnswers().stream().map(ans->{
			DriverAnswers en = new DriverAnswers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setDriverId(driverId);
			return en;
		}).collect(Collectors.toSet());
		driver.setAnswers(answer);
		driver = driverRepository.save(driver);
		return new QueAnsResponse(driver, "success", null);
	}

	@Override
	public QueAnsResponse update(QueAnsRequest request) {
		Driver driver = new Driver();
		driver.setFkEntityCode(request.getFkEntityCode());
		driver.setDriverId(request.getId());
		Set<DriverAnswers> answer = request.getQuesAnswers().stream().map(ans->{
			DriverAnswers en = new DriverAnswers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setAnswerId(ans.getAnswerId());
			return en;
		}).collect(Collectors.toSet());
		driver.setAnswers(answer);
		driver = driverRepository.save(driver);
		return new QueAnsResponse(driver, "success", null);
	}


	@Override
	public QueAnsResponse delete(QueAnsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
