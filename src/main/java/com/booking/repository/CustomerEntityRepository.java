package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.CustomerEntity;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, String> {

	List<CustomerEntity> findByEntityOwnerId(String name);

//	List<CustomerEntity> findByQuestionCategoryAndFkEntityCodeAndQuestionCodeIn(String category, String fkEntityCode,
//			List<String> qCode);

}
