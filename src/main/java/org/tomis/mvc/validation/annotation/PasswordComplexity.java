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

import org.tomis.mvc.validation.PasswordComplexityValidator;

/**
 * Project: tomis-mvc
 * @since 12.03.2010
 * @author Dan Persa
 *
 * Validation annotation used on password fields, forcing a complex password
 *
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordComplexityValidator.class)
@Documented
public @interface PasswordComplexity {

    String message() default "{org.tomis.mvc.validation.annotation.PasswordComplexity.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
