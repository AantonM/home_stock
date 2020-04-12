package com.homestock.home_stock_service.domain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnitValidator.class)
public @interface ValidUnit
{
  String message() default "Unit is not found. Please choose one of the following.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
