package org.tomis.mvc.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.tomis.mvc.validation.NoSpacesValidator;

/**
 * Project: tomis-mvc
 * @since 10.09.2010
 * @author Dan Persa
 *
 * Validation annotation used to ensure a field doesn't contain spaces
 *
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {NoSpacesValidator.class})
@Documented
public @interface NoSpaces {

    String message() default "{org.tomis.mvc.validation.annotation.NoSpaces.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
