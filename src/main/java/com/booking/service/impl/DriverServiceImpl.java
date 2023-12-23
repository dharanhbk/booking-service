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

import com.booking.entity.DriverAnswers;
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
				List<Driver> drivers = paginatedResult.toList();
				List<Questionnaire> questions = customerEntity.get().getQuestions().stream().filter(en -> 
				(Objects.nonNull(en.getQuestionCategory()) && en.getQuestionCategory().equalsIgnoreCase("DRIVER"))).toList();
				String[][] driverGrid = new String[drivers.size()+1][questions.size()+1];
				List<Questionnaire> questionsSorted = questions.stream()
						.sorted((o1,o2)->o1.getColumnOrderId().compareTo(o2.getColumnOrderId())).toList();
				List<String> header = new ArrayList<>();
				header.add("Driver Id");
				driverGrid[0][0] = "Driver Id";
				for(int i=0;i<questionsSorted.size();i++) {
					header.add(questionsSorted.get(i).getQuestionCode());
					driverGrid[0][i+1] = questionsSorted.get(i).getQuestionText();
				}
				int row =1;
				for(Driver driver:drivers) {
					driverGrid[row][0] = driver.getDriverId().toString();
					for(DriverAnswers ans:driver.getAnswers()) {
						int col = header.indexOf(ans.getQuestionCode());
						driverGrid[row][col] = ans.getAnswer();
					}
					row++;
				}
				return new QueAnsResponse(driverGrid, "success",paginatedResult.getNumberOfElements());
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
