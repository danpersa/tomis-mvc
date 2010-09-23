package org.tomis.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tomis.mvc.validation.annotation.PasswordComplexity;

/**
 * Project: tomis-mvc
 * @since 12.03.2010
 * @author Dan Persa
 */
public class PasswordComplexityValidator implements ConstraintValidator<PasswordComplexity, String> {

    @Override
    public void initialize(PasswordComplexity constraintAnnotation) {
        // nothing to do
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return false;
        }
        boolean containsNumbers = false;
        boolean containsLetters = false;
        for (int i = 0; i < object.length(); ++i) {
            char c = object.charAt(i);
            if (c >= 'A' && c <= 'z') {
                containsLetters = true;
            }
            if (c >= '0' && c <= '9') {
                containsNumbers = true;
            }
        }
        return containsLetters && containsNumbers;
    }
}
