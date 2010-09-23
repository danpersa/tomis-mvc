package org.tomis.mvc.controller.converter;

/**
 * Project: tomis-mvc
 * @since 14.03.2010
 * @author Dan Persa
 */
public interface Converter<S, T> {

    boolean canConvert(S source);

    /**
     * Convert the source of type S to target type T.
     * @param source the source object to convert, which must be an instance of S
     * @return the converted object, which must be an instance of T
     * @throws IllegalArgumentException if the source could not be converted to the desired target type
     */
    T convert(S source);
}
