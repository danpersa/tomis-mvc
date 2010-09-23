package org.tomis.mvc.model.dto;

import java.io.Serializable;

/**
 * Project: tomis-mvc
 * @since 16.04.2010
 * @author Dan Persa
 */
// must extend Dto otherwise GWT will check all Object subclasses
public class ValidationError<T extends Dto> implements Dto, Serializable {

    private String interpolatedMessage;
    private String propertyPath;
    private String messageTemplate;

    public ValidationError() {

    }

    public ValidationError(String messageTemplate, String interpolatedMessage, Object value, String propertyPath) {
        this.messageTemplate = messageTemplate;
        this.interpolatedMessage = interpolatedMessage;
        this.propertyPath = propertyPath;
    }

    public String getMessage() {
        return interpolatedMessage;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ValidationError)) {
            return false;
        }

        ValidationError that = (ValidationError) o;

        if (interpolatedMessage != null ? !interpolatedMessage.equals(that.interpolatedMessage) : that.interpolatedMessage != null) {
            return false;
        }
        if (propertyPath != null ? !propertyPath.equals(that.propertyPath) : that.propertyPath != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = interpolatedMessage != null ? interpolatedMessage.hashCode() : 0;
        result = 31 * result + (propertyPath != null ? propertyPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Error");
        sb.append("{interpolatedMessage='").append(interpolatedMessage).append('\'');
        sb.append(", propertyPath=").append(propertyPath);
        sb.append(", messageTemplate='").append(messageTemplate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
