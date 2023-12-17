package com.booking.custom.validator;

import jakarta.validation.ConstraintValidator;

public class QAValidator implements ConstraintValidator<ContactNumberConstraint, String> {

	@Override
	public void initialize(ContactNumberConstraint contactNumber) {
	}

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
		return contactField != null && contactField.matches("[0-9]+") && (contactField.length() > 8)
				&& (contactField.length() < 14);
	}

}
