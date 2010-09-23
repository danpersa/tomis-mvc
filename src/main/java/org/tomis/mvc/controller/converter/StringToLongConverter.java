package org.tomis.mvc.controller.converter;

/**
 * Project: tomis-mvc
 * @since 14.03.2010
 * @author Dan Persa
 */
public class StringToLongConverter implements Converter<String, Long> {

    @Override
    public boolean canConvert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return true;
        }
        try {
            Integer.valueOf(source);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public Long convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        return Long.valueOf(source);
    }
}
