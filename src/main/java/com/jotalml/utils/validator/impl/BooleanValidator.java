package com.jotalml.utils.validator.impl;

import static com.jotalml.utils.Constants.BOOLEAN_REGEX;

import com.jotalml.utils.validator.BooleanValidation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BooleanValidator implements ConstraintValidator<BooleanValidation, String> {

  private boolean required;

  @Override
  public void initialize(BooleanValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.required = constraintAnnotation.required();
   }

  @Override
  public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
    if (this.required) {
      return Objects.nonNull(field) && validateField(field);
    }
    return Objects.isNull(field) || validateField(field);
  }

  private boolean validateField(String field) {
    if (field.isEmpty()) {
      return false;
    }
    return BOOLEAN_REGEX.matcher(field).matches();
  }
}
