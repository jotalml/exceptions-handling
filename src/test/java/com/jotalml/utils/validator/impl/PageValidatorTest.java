package com.jotalml.utils.validator.impl;

import com.jotalml.utils.validator.PageValidation;
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
class PageValidatorTest {

  private PageValidator validator = new PageValidator();
  @Mock private PageValidation pageValidation;

  @ParameterizedTest
  @ValueSource(strings = {"0", "1", "100"})
  void pageValidator_ShouldReturnTrue_WhenFieldValueIsValidIsNotRequired(String page) {
    when(pageValidation.required()).thenReturn(false);
    validator.initialize(pageValidation);
    assertTrue(isValid(page));
  }

  @ParameterizedTest
  @ValueSource(strings = {"001", "qwerty", "-1", "-01"})
  void pageValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndIsNotRequired(String page) {
    when(pageValidation.required()).thenReturn(false);
    validator.initialize(pageValidation);
    assertFalse(isValid(page));
  }

  @ParameterizedTest
  @NullSource
  void pageValidator_ShouldReturnTrue_WhenFieldValueNullAndIsNotRequired(String page) {
    when(pageValidation.required()).thenReturn(false);
    validator.initialize(pageValidation);
    assertTrue(isValid(page));
  }

  @ParameterizedTest
  @ValueSource(strings = {"0", "1", "100"})
  void pageValidator_ShouldReturnTrue_WhenFieldValueIsValidAndIsRequired(String page) {
    when(pageValidation.required()).thenReturn(true);
    validator.initialize(pageValidation);
    assertTrue(isValid(page));
  }

  @ParameterizedTest
  @ValueSource(strings = {"001", "qwerty", "-1", "-01"})
  void pageValidator_ShouldReturnFalse_WhenFieldValueIsNotValidAndIsRequired(String page) {
    when(pageValidation.required()).thenReturn(true);
    validator.initialize(pageValidation);
    assertFalse(isValid(page));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void pageValidator_ShouldReturnFalse_WhenFieldValueNullAndEmptyAndIsRequired(String page) {
    when(pageValidation.required()).thenReturn(true);
    validator.initialize(pageValidation);
    assertFalse(isValid(page));
  }

  private boolean isValid(String value) {
    return validator.isValid(value, null);
  }
}
