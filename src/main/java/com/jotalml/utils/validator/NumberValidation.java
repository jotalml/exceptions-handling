package com.jotalml.utils.validator;

import com.jotalml.utils.validator.impl.NumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.jotalml.utils.Constants.NUMBER_VALIDATION;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NumberValidator.class)
public @interface NumberValidation {

  public String message() default NUMBER_VALIDATION;

  public boolean required() default false;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  public boolean includeNegatives() default false;

  public boolean includeDecimal() default false;
}
