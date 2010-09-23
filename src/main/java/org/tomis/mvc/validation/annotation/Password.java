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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Project: tomis-mvc
 * @since 12.03.2010
 * @author Dan Persa
 *
 * Validation annotation used on password fields, forcing min and max lengths
 * and complexity for a password
 *
 */
@NotEmpty
@Size(min = 4, max = 14)
@PasswordComplexity
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface Password {

    String message() default "{org.tomis.mvc.validation.annotation.Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
