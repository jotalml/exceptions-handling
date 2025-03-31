package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.NumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

import static com.jotalml.utils.Constants.NUMBER_REGEX;
import static com.jotalml.utils.Constants.NUMBER_WITH_DECIMAL_NEGATIVE_REGEX;
import static com.jotalml.utils.Constants.NUMBER_WITH_DECIMAL_REGEX;
import static com.jotalml.utils.Constants.NUMBER_WITH_NEGATIVE_REGEX;

public class NumberValidator implements ConstraintValidator<NumberValidation, String> {

  private boolean required;
  private boolean includeNegatives;
  private boolean includeDecimal;


  @Override
  public void initialize(NumberValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.required = constraintAnnotation.required();
    this.includeNegatives = constraintAnnotation.includeNegatives();
    this.includeDecimal = constraintAnnotation.includeDecimal();
  }

  @Override
  public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
    if (this.required) {
      return Objects.nonNull(field) && validateField(field);
    }
    return Objects.isNull(field) || validateField(field);
  }

  private boolean validateField(String field) {
    boolean response = false;
    if(this.includeNegatives && !this.includeDecimal)
      response= NUMBER_WITH_NEGATIVE_REGEX.matcher(field).matches();
    if(!this.includeNegatives && this.includeDecimal )
      response= NUMBER_WITH_DECIMAL_REGEX.matcher(field).matches();
    if(!this.includeNegatives && !this.includeDecimal)
      response= NUMBER_REGEX.matcher(field).matches();
    if(this.includeNegatives && this.includeDecimal)
      response= NUMBER_WITH_DECIMAL_NEGATIVE_REGEX.matcher(field).matches();
    return  response;
  }
}
