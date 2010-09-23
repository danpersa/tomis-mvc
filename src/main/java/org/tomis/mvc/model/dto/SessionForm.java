package org.tomis.mvc.model.dto;

import java.io.Serializable;

/**
 * Project: tomis-mvc
 * @since 10.03.2010
 * @author Dan Persa
 */
public class SessionForm<CommandObject extends Dto> implements Serializable {

    private ValidationErrors<CommandObject> bindErrors;
    private ValidationErrors<CommandObject> validationErrors;
    private CommandObject commandObject;

    public SessionForm(CommandObject commandObject, ValidationErrors<CommandObject> bindErrors, ValidationErrors<CommandObject> validationErrors) {
        this.bindErrors = bindErrors;
        this.validationErrors = validationErrors;
        this.commandObject = commandObject;
    }

    public ValidationErrors<CommandObject> getBindErrors() {
        return bindErrors;
    }

    public void setBindErrors(ValidationErrors<CommandObject> bindErrors) {
        this.bindErrors = bindErrors;
    }

    public ValidationErrors<CommandObject> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(ValidationErrors<CommandObject> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public CommandObject getCommandObject() {
        return commandObject;
    }

    public void setCommandObject(CommandObject commandObject) {
        this.commandObject = commandObject;
    }
}
