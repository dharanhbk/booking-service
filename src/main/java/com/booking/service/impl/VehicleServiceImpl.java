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

import com.booking.entity.VehicleAnswers;
import com.booking.entity.Vehicle;
import com.booking.entity.CustomerEntity;
import com.booking.entity.Questionnaire;
import com.booking.model.request.QueAnsRequest;
import com.booking.model.response.QueAnsResponse;
import com.booking.repository.VehicleRepository;
import com.booking.repository.CustomerEntityRepository;
import com.booking.service.QueAnsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("vehicle")
public class VehicleServiceImpl implements QueAnsService {
	
	private final VehicleRepository vehicleRepository;
	
	private final CustomerEntityRepository customerEntityRepository;
	
	@Override
	public QueAnsResponse findById(Optional<Long> vehicleId) {
		if(vehicleId.isPresent()) 
			return new QueAnsResponse(vehicleRepository.findById(vehicleId.get()),"success",null);
		return new QueAnsResponse(null, "Invalid Request",null);
	}
	
	@Override
	public QueAnsResponse findAllByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize) {
		if(entityCode.isPresent()) {
			Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(entityCode.get());
			Pageable page = PageRequest.of(pageNo, fetchSize, Sort.by("vehicleId").descending());
			Page<Vehicle> paginatedResult = vehicleRepository.findByFkEntityCode(entityCode.get(),page);
			if(paginatedResult.hasContent() && customerEntity.isPresent()) {
				List<Vehicle> vehicles = paginatedResult.toList();
				List<Questionnaire> questions = customerEntity.get().getQuestions().stream().filter(en -> 
				(Objects.nonNull(en.getQuestionCategory()) && en.getQuestionCategory().equalsIgnoreCase("VEHICLE"))).toList();
				String[][] vehicleGrid = new String[vehicles.size()+1][questions.size()+1];
				List<Questionnaire> questionsSorted = questions.stream()
						.sorted((o1,o2)->o1.getColumnOrderId().compareTo(o2.getColumnOrderId())).toList();
				List<String> header = new ArrayList<>();
				header.add("Vehicle Id");
				vehicleGrid[0][0] = "Vehicle Id";
				for(int i=0;i<questionsSorted.size();i++) {
					header.add(questionsSorted.get(i).getQuestionCode());
					vehicleGrid[0][i+1] = questionsSorted.get(i).getQuestionText();
				}
				int row =1;
				for(Vehicle vehicle:vehicles) {
					vehicleGrid[row][0] = vehicle.getVehicleId().toString();
					for(VehicleAnswers ans:vehicle.getAnswers()) {
						int col = header.indexOf(ans.getQuestionCode());
						vehicleGrid[row][col] = ans.getAnswer();
					}
					row++;
				}
				return new QueAnsResponse(vehicleGrid, "success",paginatedResult.getNumberOfElements());
			}
		}
		return new QueAnsResponse(null, "Invalid Request",null);
		
	}

	@Override
	public QueAnsResponse save(QueAnsRequest request) {
		Vehicle vehicle = new Vehicle();
		vehicle.setFkEntityCode(request.getFkEntityCode());
		vehicle = vehicleRepository.save(vehicle);
		final Long vehicleId = vehicle.getVehicleId();
		Set<VehicleAnswers> answer = request.getQuesAnswers().stream().map(ans->{
			VehicleAnswers en = new VehicleAnswers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setVehicleId(vehicleId);
			return en;
		}).collect(Collectors.toSet());
		vehicle.setAnswers(answer);
		vehicle = vehicleRepository.save(vehicle);
		return new QueAnsResponse(vehicle, "success", null);
	}

	@Override
	public QueAnsResponse update(QueAnsRequest request) {
		Vehicle vehicle = new Vehicle();
		vehicle.setFkEntityCode(request.getFkEntityCode());
		vehicle.setVehicleId(request.getId());
		Set<VehicleAnswers> answer = request.getQuesAnswers().stream().map(ans->{
			VehicleAnswers en = new VehicleAnswers();
			en.setAnswer(ans.getAnswer());
			en.setQuestionCode(ans.getQuestionCode());
			en.setAnswerId(ans.getAnswerId());
			return en;
		}).collect(Collectors.toSet());
		vehicle.setAnswers(answer);
		vehicle = vehicleRepository.save(vehicle);
		return new QueAnsResponse(vehicle, "success", null);
	}

	@Override
	public QueAnsResponse delete(QueAnsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
