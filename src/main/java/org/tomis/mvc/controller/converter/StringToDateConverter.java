package org.tomis.mvc.controller.converter;

import java.text.ParseException;
import java.util.Date;
import org.tomis.mvc.controller.helper.Helper;

/**
 * Project: tomis-mvc
 * @since 24.03.2010
 * @author Dan Persa
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public boolean canConvert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return true;
        }
        try {
            Helper.DATE_FORMAT.parse(source);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Date convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        try {
            return Helper.DATE_FORMAT.parse(source);
        } catch (ParseException ex) {
            return null;
        }
    }
}
