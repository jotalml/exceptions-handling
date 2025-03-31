package com.jotalml.utils.validator;

import static com.jotalml.utils.Constants.BOOLEAN_VALIDATION;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.jotalml.utils.validator.impl.BooleanValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BooleanValidator.class)
public @interface BooleanValidation {

  public String message() default BOOLEAN_VALIDATION;

  public boolean required() default false;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
