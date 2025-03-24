package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.ValueOfEnumValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator
    implements ConstraintValidator<ValueOfEnumValidation, CharSequence> {
  private boolean isRequired;
  private List<String> acceptedValues;

  @Override
  public void initialize(ValueOfEnumValidation annotation) {
    isRequired = annotation.required();
    acceptedValues =
        Stream.of(annotation.enumClass().getEnumConstants())
            .map(Enum::toString)
            .collect(Collectors.toList());
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    if (Objects.isNull(value) && isRequired) {
      return false;
    }
    if (Objects.isNull(value)) {
      return true;
    }
    return acceptedValues.contains(value.toString().toLowerCase());
  }
}
