package org.tomis.mvc.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.log4j.Logger;
import org.tomis.mvc.model.service.jpa.impl.BaseCrudServiceBean;
import org.tomis.mvc.validation.annotation.FieldMatch;

/**
 * Project: tomis-mvc
 * @since 21.09.2010
 * @author Dan Persa
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private static Logger logger = Logger.getLogger(BaseCrudServiceBean.class);
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        logger.info("field match validator first: " + firstFieldName + "|second: " + secondFieldName);

        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            boolean b = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            logger.info("return " + b);
            return b;
        }
        catch (final Exception ignore) {
            logger.error("cannot validate: " + ignore);
        }
        logger.info("return false");
        return false;
    }
}
