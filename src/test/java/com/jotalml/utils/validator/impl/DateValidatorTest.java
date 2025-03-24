package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.DateValidation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DateValidatorTest {

  private final DateValidator validator = new DateValidator();
  @Mock private DateValidation validation;

  @ParameterizedTest
  @ValueSource(strings = {"2016-06-01", "2022-01-01"})
  void dateValidator_ShouldReturnTrue_WhenFieldDateIsValidAndAndNotRequired(String date) {
    when(validation.required()).thenReturn(false);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertTrue(isValid(date));
  }

  @ParameterizedTest
  @ValueSource(strings = {"2016-06-01", "2022-01-01"})
  void dateValidator_ShouldReturnTrue_WhenFieldDateIsValidAndAndRequired(String date) {
    when(validation.required()).thenReturn(true);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertTrue(isValid(date));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void dateValidator_ShouldReturnFalse_WhenFieldDateIsNullOrEmptyAndRequired(String date) {
    when(validation.required()).thenReturn(true);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertFalse(isValid(date));
  }

  @ParameterizedTest
  @NullSource
  void dateValidator_ShouldReturnTrue_WhenFieldDateIsNullAndNotRequired(String date) {
    when(validation.required()).thenReturn(false);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertTrue(isValid(date));
  }

  @ParameterizedTest
  @ValueSource(strings = {"2016/06/01", "01-02-2022"})
  void dateValidator_ShouldReturnFalse_WhenFieldDateIsInvalidAndNotRequired(String date) {
    when(validation.required()).thenReturn(false);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertFalse(isValid(date));
  }

  @ParameterizedTest
  @ValueSource(strings = {"2016/06/01", "01-02-2022"})
  void dateValidator_ShouldReturnFalse_WhenFieldDateIsInvalidAndRequired(String date) {
    when(validation.required()).thenReturn(true);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertFalse(isValid(date));
  }

  @ParameterizedTest
  @EmptySource
  void dateValidator_ShouldReturnFalse_WhenFieldDateIsEmptyAndNotRequired(String date) {
    when(validation.required()).thenReturn(false);
    when(validation.pattern()).thenReturn("yyyy-MM-dd");
    validator.initialize(validation);
    assertFalse(isValid(date));
  }

  private boolean isValid(String value) {
    return validator.isValid(value, null);
  }
}
