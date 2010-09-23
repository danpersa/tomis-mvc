package org.tomis.mvc.model.dto;

/**
 * Project: tomis-mvc
 * @since 01.09.2010
 * @author Dan Persa
 */
public class Pair<T, R> {

    private T theKey;
    private T theValue;

    public Pair() {
    }

    public Pair(T key, T value) {
        this.theKey = key;
        this.theValue = value;
    }

    public T getTheKey() {
        return theKey;
    }

    public void setTheKey(T theKey) {
        this.theKey = theKey;
    }

    public T getTheValue() {
        return theValue;
    }

    public void setTheValue(T theValue) {
        this.theValue = theValue;
    }

    @Override
    public String toString() {
        return "Pair{" + "[theKey=" + theKey + "][theValue=" + theValue + "]}";
    }
}
