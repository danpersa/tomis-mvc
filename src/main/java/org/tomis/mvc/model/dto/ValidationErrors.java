package org.tomis.mvc.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Project: tomis-mvc
 * @since 12.03.2010
 * @author Dan Persa
 */
// must extend Dto otherwise GWT will check all Object subclasses
public class ValidationErrors<CommandObject extends Dto> implements Dto, Serializable {

    private HashSet<ValidationError<CommandObject>> validationErrors;

    public ValidationErrors() {
        validationErrors = new HashSet<ValidationError<CommandObject>>();
    }

    public void add(ValidationError<CommandObject> error) {
        getValidationErrors().add(error);
    }

    public void addAll(HashSet<ValidationError<CommandObject>> errors) {
        getValidationErrors().addAll(errors);
    }

    public boolean isEmpty() {
        if (validationErrors == null || validationErrors.isEmpty()) {
            return true;
        }
        return false;
    }

    public Set<ValidationError<CommandObject>> getValidationErrors() {
        return validationErrors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[Errors: [ValidationErrors: ");
        if (getValidationErrors() != null && !getValidationErrors().isEmpty()) {
            for (ValidationError<CommandObject> cv : getValidationErrors()) {
                sb.append("[ path:|").append(cv.getPropertyPath()).append("| message: ").append(cv.getMessage()).append("]");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
