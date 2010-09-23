package org.tomis.mvc.model.dto;

import java.io.Serializable;

/**
 * Project: tomis-mvc
 * @since 16.04.2010
 * @author Dan Persa
 */
public interface Dto extends Serializable {

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();
}
