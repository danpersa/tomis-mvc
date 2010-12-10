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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<T, R> other = (Pair<T, R>) obj;
        if (this.theKey != other.theKey && (this.theKey == null || !this.theKey.equals(other.theKey))) {
            return false;
        }
        if (this.theValue != other.theValue && (this.theValue == null || !this.theValue.equals(other.theValue))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.theKey != null ? this.theKey.hashCode() : 0);
        hash = 71 * hash + (this.theValue != null ? this.theValue.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Pair{" + "[theKey=" + theKey + "][theValue=" + theValue + "]}";
    }
}
