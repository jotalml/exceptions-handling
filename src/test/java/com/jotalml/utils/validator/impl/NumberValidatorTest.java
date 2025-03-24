package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.NumberValidation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NumberValidatorTest {

  private final NumberValidator validator = new NumberValidator();
  @Mock private NumberValidation validation;

  @ParameterizedTest
  @ValueSource(strings = {"1", "2", "3", "40", "100"})
  void numberValidator_ShouldReturnTrue_WhenFieldValueIsValidAndRequiredAndExcludeNegatives(
      String field) {
    when(validation.required()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(field));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"01", "-2", "003", "qwerty", "qwerty123"})
  void numberValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndRequiredAndExcludeNegatives(
      String field) {
    when(validation.required()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertFalse(isValid(field));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"1", "2", "3", "40", "100"})
  void numberValidator_ShouldReturnTrue_WhenFieldValueIsNotValidAndNotRequiredAndExcludeNegatives(
      String field) {
    when(validation.required()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(field));
  }

  @ParameterizedTest
  @ValueSource(strings = {"01", "-2", "003", "qwerty", "qwerty123", ""})
  void numberValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndNotRequiredAndExcludeNegatives(
      String field) {
    when(validation.required()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertFalse(isValid(field));
  }

  @ParameterizedTest
  @ValueSource(strings = {"1", "-2", "3", "40", "100", "0"})
  void numberValidator_ShouldReturnTrue_WhenFieldValueIsValidAndRequiredAndIncludeNegatives(
      String field) {
    when(validation.required()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertTrue(isValid(field));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"01", "003", "qwerty", "qwerty123"})
  void numberValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndRequiredAndIncludeNegatives(
      String field) {
    when(validation.required()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertFalse(isValid(field));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"1", "2", "3", "40", "100"})
  void numberValidator_ShouldReturnTrue_WhenFieldValueIsNotValidAndNotRequiredAndIncludeNegatives(
      String field) {
    when(validation.required()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertTrue(isValid(field));
  }

  @ParameterizedTest
  @ValueSource(strings = {"01", "003", "qwerty", "qwerty123", " "})
  void numberValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndNotRequiredAndIncludeNegatives(
      String field) {
    when(validation.required()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertFalse(isValid(field));
  }

  private boolean isValid(String value) {
    return validator.isValid(value, null);
  }
}
