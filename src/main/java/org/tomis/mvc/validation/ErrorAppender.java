package org.tomis.mvc.validation;

import java.util.Collection;

import javax.validation.ConstraintViolation;

import org.tomis.mvc.model.dto.Dto;
import org.tomis.mvc.model.dto.ValidationError;
import org.tomis.mvc.model.dto.ValidationErrors;

/**
 * Project: tomis-mvc
 * @since 16.04.2010
 * @author Dan Persa
 */
public class ErrorAppender<CommandObject extends Dto> {

    public void add(ConstraintViolation<CommandObject> constraintViolation, ValidationErrors<CommandObject> errors) {
        ValidationError<CommandObject> error = new ValidationError<CommandObject>(constraintViolation.getMessageTemplate(), constraintViolation.getMessage(), constraintViolation
                .getInvalidValue(), constraintViolation.getPropertyPath().toString());
        errors.getValidationErrors().add(error);
    }

    public void addAll(Collection<ConstraintViolation<CommandObject>> constraintViolations, ValidationErrors<CommandObject> errors) {

        for (ConstraintViolation<CommandObject> constraintViolation : constraintViolations) {
            ValidationError<CommandObject> error = new ValidationError<CommandObject>(constraintViolation.getMessageTemplate(), constraintViolation.getMessage(),
                    constraintViolation.getInvalidValue(), constraintViolation.getPropertyPath().toString());
            errors.getValidationErrors().add(error);
        }
    }
}
