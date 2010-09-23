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
public class DualErrorAppender<SourceCommandObject extends Dto, DestCommandObject extends Dto> {

    public void add(ConstraintViolation<SourceCommandObject> constraintViolation, ValidationErrors<DestCommandObject> errors) {
        ValidationError<DestCommandObject> error = new ValidationError<DestCommandObject>(constraintViolation.getMessageTemplate(), constraintViolation.getMessage(),
                constraintViolation.getInvalidValue(), constraintViolation.getPropertyPath().toString());
        errors.getValidationErrors().add(error);
    }

    public void addAll(Collection<ConstraintViolation<SourceCommandObject>> constraintViolations, ValidationErrors<DestCommandObject> errors) {

        for (ConstraintViolation<SourceCommandObject> constraintViolation : constraintViolations) {
            ValidationError<DestCommandObject> error = new ValidationError<DestCommandObject>(constraintViolation.getMessageTemplate(), constraintViolation.getMessage(),
                    constraintViolation.getInvalidValue(), constraintViolation.getPropertyPath().toString());
            errors.getValidationErrors().add(error);
        }
    }
}
