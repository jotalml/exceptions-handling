package com.jotalml.utils.validator.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.jotalml.utils.validator.BooleanValidation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BooleanValidatorTest {

  private final BooleanValidator validator = new BooleanValidator();
  @Mock private BooleanValidation validation;

  @ParameterizedTest
  @ValueSource(strings = {"true", "false"})
  void booleanValidator_ShouldReturnTrue_WhenFieldValueIsValidAndRequired(
      String field) {
    when(validation.required()).thenReturn(true);
    validator.initialize(validation);
    assertTrue(isValid(field));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"01", "-2", "003", "True", "fAlse"})
  void booleanValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndRequired(
      String field) {
    when(validation.required()).thenReturn(true);
    validator.initialize(validation);
    assertFalse(isValid(field));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"true", "false"})
  void booleanValidator_ShouldReturnTrue_WhenFieldValueIsNotValidAndNotRequired(
      String field) {
    when(validation.required()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(field));
  }

  @ParameterizedTest
  @ValueSource(strings = {"01", "-2", "003", "qwerty", "truE", ""})
  void booleanValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndNotRequired(
      String field) {
    when(validation.required()).thenReturn(false);
    validator.initialize(validation);
    assertFalse(isValid(field));
  }

  private boolean isValid(String value) {
    return validator.isValid(value, null);
  }
}
