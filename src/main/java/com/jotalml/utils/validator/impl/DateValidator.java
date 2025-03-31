package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.DateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateValidator implements ConstraintValidator<DateValidation, String> {

  private boolean isRequired;
  private DateTimeFormatter df;

  @Override
  public void initialize(DateValidation annotation) {
    isRequired = annotation.required();
    df = DateTimeFormatter.ofPattern(annotation.pattern());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if ((Objects.isNull(value) || value.trim().isEmpty()) && isRequired) {
      return false;
    }
    if (Objects.isNull(value)) {
      return true;
    }
    try {
      LocalDate.parse(value, df);
    } catch (Exception ex) {
      return false;
    }
    return true;
  }
}
