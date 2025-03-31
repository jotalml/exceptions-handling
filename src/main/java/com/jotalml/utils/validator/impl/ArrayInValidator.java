package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.ArrayInValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

import static com.jotalml.utils.Constants.*;

public class ArrayInValidator implements ConstraintValidator<ArrayInValidation, String> {

  private boolean required;
  private boolean onlyNumbers;
  private boolean includeNegatives;
  private boolean includeDecimal;

  @Override
  public void initialize(ArrayInValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.required = constraintAnnotation.required();
    this.onlyNumbers = constraintAnnotation.onlyNumbers();
    this.includeNegatives = constraintAnnotation.includeNegatives();
    this.includeDecimal = constraintAnnotation.includeDecimal();
  }

  @Override
  public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
    if (required) {
      return Objects.nonNull(field) && !field.trim().isEmpty() && validateField(field);
    } else {
      return Objects.isNull(field) || validateField(field);
    }
  }

  private boolean validateField(String field) {
    return onlyNumbers
        ? (includeNegatives
            ? ( includeDecimal
                ? ARRAY_NUMBERS_WITH_DECIMAL_NEGATIVE_REGEX.matcher(field).matches()
                : ARRAY_NUMBERS_WITH_NEGATIVE_REGEX.matcher(field).matches())
            : ( includeDecimal
                ? ARRAY_NUMBERS_WITH_DECIMAL_REGEX.matcher(field).matches()
                :ARRAY_NUMBERS_REGEX.matcher(field).matches()))
        : ARRAY_STRING_REGEX.matcher(field).matches();
  }
}
