package com.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="questionnaire",schema="booking")
public class Questionnaire extends AuditEntity<String> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id",nullable=false)
	private Long questionId;
	@Column(name="question_text")
	private String questionText;
	@Column(name="question_desc")
	private String questionDesc;
	@Column(name="possible_answers")
	private String possibleAnswers;
	@Column(name="question_type")
	private String questionType;
	@Column(name="question_datatype")
	private String questionDataType;
//	@Column(name="additional_spec")
//	private String additionalSpec;
	@Column(name="is_main_mandatory")
	private Boolean isMainMandatory;
	@Column(name="question_code")
	private String questionCode;
	@Column(name="parent_question_code")
	private String parentQuestionCode;
	@Column(name="fk_entity_code")
	private String fkEntityCode;
	@Column(name="question_version")
	private Integer questionVersion;
	@Column(name="question_category")
	private String questionCategory;
	@Column(name="is_active_ind")
	private boolean isActiveInd;
	@Column(name="is_visible")
	private boolean isVisible;
	@Column(name="is_required_field")
	private boolean isRequiredField;
	
	@Column(name="column_order_id")
	private Integer columnOrderId;
	
	
	

}
