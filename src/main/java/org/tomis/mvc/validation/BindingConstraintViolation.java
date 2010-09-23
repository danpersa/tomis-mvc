package org.tomis.mvc.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import org.hibernate.validator.engine.PathImpl;

/**
 * Project: tomis-mvc
 * @since 15.03.2010
 * @author Dan Persa
 */
public class BindingConstraintViolation<T> implements ConstraintViolation<T> {

    private String message;
    private String messageTemplate;
    private T rootBean;
    private Object invalidValue;
    private String stringPath;

    public BindingConstraintViolation(String message, String messageTemplate, T rootBean, Object invalidValue, String stringPath) {
        this.message = message;
        this.messageTemplate = messageTemplate;
        this.rootBean = rootBean;
        this.invalidValue = invalidValue;
        this.stringPath = stringPath;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageTemplate() {
        return messageTemplate;
    }

    @Override
    public T getRootBean() {
        return rootBean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> getRootBeanClass() {
        return (Class<T>) rootBean.getClass();
    }

    @Override
    public Object getLeafBean() {
        return null;
    }

    @Override
    public Path getPropertyPath() {
        Path p = PathImpl.createPathFromString(stringPath);
        return p;
    }

    @Override
    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return null;
    }
}
