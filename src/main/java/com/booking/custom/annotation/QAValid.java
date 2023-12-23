//package com.booking.custom.annotation;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import com.booking.custom.validator.QAValidator;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//@Documented
//@Constraint(validatedBy = QAValidator.class)
//@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.TYPE })
//@Retention(RetentionPolicy.RUNTIME)
//public @interface QAValid {
//	
//    String message() default "Invalid phone number";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
