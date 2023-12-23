package com.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long>{

	List<Questionnaire> findByFkEntityCode(String entityCode);

	List<Questionnaire> findByFkEntityCodeAndQuestionCategory(String entityCode, String questionCategory);

}
