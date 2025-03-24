package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.ArrayInValidation;
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
class ArrayInValidatorTest {

  private final ArrayInValidator validator = new ArrayInValidator();
  @Mock private ArrayInValidation validation;

  @ParameterizedTest
  @ValueSource(strings = {"1", "1,2,3", "10,11,12", "100,101,102"})
  void arrayInValidator_ShouldReturnTrue_WhenFieldArrayAreValidAndIsOnlyNumberAndRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(true);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(arrayIn));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"01,02,03", "qwerty,ytrewq,hello", "0"})
  void arrayInValidator_ShouldReturnFalse_WhenFieldArrayAreInvalidAndIsOnlyNumberAndRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(true);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertFalse(isValid(arrayIn));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"1", "1,2,3", "10,11,12", "100,101,102"})
  void arrayInValidator_ShouldReturnTrue_WhenFieldArrayAreValidAndIsOnlyNumberAndNotRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(false);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(arrayIn));
  }

  @ParameterizedTest
  @ValueSource(strings = {"01,02,03", "qwerty,ytrewq,hello", "0"})
  void arrayInValidator_ShouldReturnFalse_WhenFieldArrayAreInvalidAndIsOnlyNumberAndNotRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(false);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertFalse(isValid(arrayIn));
  }

  @ParameterizedTest
  @ValueSource(strings = {"1", "MD,LAJA,ENERGIA,BTP", "qwerty", "100,101,102", "01, 02, MD"})
  void arrayInValidator_ShouldReturnTrue_WhenFieldArrayAreValidAndIsNotOnlyNumberAndRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(true);
    when(validation.onlyNumbers()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(arrayIn));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void arrayInValidator_ShouldReturnFalse_WhenFieldArrayAreInvalidAndIsNotOnlyNumberAndRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(true);
    when(validation.onlyNumbers()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertFalse(isValid(arrayIn));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"1", "MD,LAJA,ENERGIA,BTP", "qwerty", "100,101,102", "01, 02, MD"})
  void arrayInValidator_ShouldReturnTrue_WhenFieldArrayAreValidAndIsNotOnlyNumberAndNotRequired(
      String arrayIn) {
    when(validation.required()).thenReturn(false);
    when(validation.onlyNumbers()).thenReturn(false);
    when(validation.includeNegatives()).thenReturn(false);
    validator.initialize(validation);
    assertTrue(isValid(arrayIn));
  }

  @ParameterizedTest
  @ValueSource(strings = {"1", "1,2,3", "10,11,12", "100,101,102"})
  void
      arrayInValidator_ShouldReturnTrue_WhenFieldArrayAreValidAndIsOnlyNumberAndRequiredAndIncludeNegatives(
          String arrayIn) {
    when(validation.required()).thenReturn(true);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertTrue(isValid(arrayIn));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"01,02,03", "qwerty,ytrewq,hello"})
  void
      arrayInValidator_ShouldReturnFalse_WhenFieldArrayAreInvalidAndIsOnlyNumberAndRequiredAndIncludeNegatives(
          String arrayIn) {
    when(validation.required()).thenReturn(true);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertFalse(isValid(arrayIn));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"-1", "0", "0,-1,3", "10,11,12", "-100,-101,-102"})
  void
      arrayInValidator_ShouldReturnTrue_WhenFieldArrayAreValidAndIsOnlyNumberAndNotRequiredAndIncludeNegatives(
          String arrayIn) {
    when(validation.required()).thenReturn(false);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertTrue(isValid(arrayIn));
  }

  @ParameterizedTest
  @ValueSource(strings = {"01,02,03", "qwerty,ytrewq,hello"})
  void
      arrayInValidator_ShouldReturnFalse_WhenFieldArrayAreInvalidAndIsOnlyNumberAndNotRequiredAndIncludeNegatives(
          String arrayIn) {
    when(validation.required()).thenReturn(false);
    when(validation.onlyNumbers()).thenReturn(true);
    when(validation.includeNegatives()).thenReturn(true);
    validator.initialize(validation);
    assertFalse(isValid(arrayIn));
  }

  private boolean isValid(String value) {
    return validator.isValid(value, null);
  }
}
