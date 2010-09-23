package org.tomis.mvc.controller.binder;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.validation.ConstraintViolation;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.tomis.mvc.controller.converter.StringToDateConverter;
import org.tomis.mvc.controller.converter.StringToIntegerConverter;
import org.tomis.mvc.controller.converter.StringToLongConverter;
import org.tomis.mvc.model.dto.ClassFromString;
import org.tomis.mvc.model.dto.Dto;
import org.tomis.mvc.model.dto.ValidationErrors;
import org.tomis.mvc.model.entity.PersistentEntity;
import org.tomis.mvc.validation.BindingConstraintViolation;
import org.tomis.mvc.validation.ErrorAppender;

/**
 * Project: tomis-mvc
 * @since 24.03.2010
 * @author Dan Persa
 */
public class Binder<CommandObject extends Dto> {

    private static Logger logger = Logger.getLogger(Binder.class);

    public void bind(CommandObject commandObject, String field, Object value) {
        if (value == null) {
            logger.warn("bind return because of null!!");
            return;
        }
        try {
            // should length be validated?
            BeanUtils.setProperty(commandObject, field, value);
            logger.info("bind: " + commandObject + " field: " + field + " value: " + value + " valueClass: " + value.getClass().getSimpleName());
        }
        catch (IllegalAccessException ex) {
            logger.error("IllegalAccessException: " + ex);
        }
        catch (InvocationTargetException ex) {
            logger.error("InvocationTargetException: " + ex);
        }
    }

    public void bindString(CommandObject commandObject, String field, String value) {
        bind(commandObject, field, value);
    }

    public void bindLong(CommandObject commandObject, String field, String value, ValidationErrors<CommandObject> errors) {
        StringToLongConverter longConverter = new StringToLongConverter();
        if (longConverter.canConvert(value)) {
            Long l = longConverter.convert(value);
            bind(commandObject, field, l);
        } else {
            ConstraintViolation<CommandObject> cv = new BindingConstraintViolation<CommandObject>("Campul trebuie sa fie numeric", "template",
                    commandObject, value, field);
            ErrorAppender<CommandObject> errorAppender = new ErrorAppender<CommandObject>();
            errorAppender.add(cv, errors);
        }
    }

    public void bindInteger(CommandObject commandObject, String field, String value, ValidationErrors<CommandObject> errors) {
        StringToIntegerConverter longConverter = new StringToIntegerConverter();
        if (longConverter.canConvert(value)) {
            Integer l = longConverter.convert(value);
            bind(commandObject, field, l);
        } else {
            ConstraintViolation<CommandObject> cv = new BindingConstraintViolation<CommandObject>("Campul trebuie sa fie numeric", "template",
                    commandObject, value, field);
            ErrorAppender<CommandObject> errorAppender = new ErrorAppender<CommandObject>();
            errorAppender.add(cv, errors);
        }
    }

    public void bindDate(CommandObject commandObject, String field, String value, ValidationErrors<CommandObject> errors) {
        StringToDateConverter converter = new StringToDateConverter();
        Date date = null;
        if (converter.canConvert(value)) {
            date = converter.convert(value);
            bind(commandObject, field, date);
        } else {
            ConstraintViolation<CommandObject> cv = new BindingConstraintViolation<CommandObject>("Campul trebuie sa fie o data in formatul dd/MM/yyyy",
                    "template", commandObject, value, field);
            ErrorAppender<CommandObject> errorAppender = new ErrorAppender<CommandObject>();
            errorAppender.add(cv, errors);
        }
    }

    public void bindEntity(CommandObject commandObject, String field, String value, PersistentEntity<Long> entity, ValidationErrors<CommandObject> errors) {
        StringToLongConverter idConverter = new StringToLongConverter();
        if (idConverter.canConvert(value)) {
            Long entityId = idConverter.convert(value);
            entity.setId(entityId);
            bind(commandObject, field, entity);
        } else {
            ConstraintViolation<CommandObject> cv = new BindingConstraintViolation<CommandObject>("Campul " + "trebuie sa fie numeric", "template",
                    commandObject, value, field);
            ErrorAppender<CommandObject> errorAppender = new ErrorAppender<CommandObject>();
            errorAppender.add(cv, errors);
        }
    }

    public void bindClassFromString(CommandObject commandObject, String field, String value, ClassFromString dependentClass, ValidationErrors<CommandObject> errors) {
        Object o = dependentClass.getFromString(value);
        if (o == null) {
            ConstraintViolation<CommandObject> cv = new BindingConstraintViolation<CommandObject>("Campul nu poate fi binduit", "template",
                    commandObject, value, field);
            ErrorAppender<CommandObject> errorAppender = new ErrorAppender<CommandObject>();
            errorAppender.add(cv, errors);
            return;
        }
        bind(commandObject, field, o);
    }
}
