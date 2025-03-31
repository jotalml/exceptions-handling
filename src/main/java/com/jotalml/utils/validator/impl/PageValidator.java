package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.PageValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

import static com.jotalml.utils.Constants.NUMBER_REGEX;

public class PageValidator implements ConstraintValidator<PageValidation, String> {

  private boolean required;

  @Override
  public void initialize(PageValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.required = constraintAnnotation.required();
  }

  @Override
  public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
    if (required) {
      return Objects.nonNull(field) && validateField(field);
    } else {
      return Objects.isNull(field) || validateField(field);
    }
  }

  private boolean validateField(String field) {
    if (field.isEmpty()) {
      return false;
    }
    if (field.length() == 1 && field.equals("0")) {
      return true;
    }
    return NUMBER_REGEX.matcher(field).matches();
  }
}
