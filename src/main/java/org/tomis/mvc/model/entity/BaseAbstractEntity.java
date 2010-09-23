package org.tomis.mvc.model.entity;

/**
 * 
 * @author Dr. Spock (spock at dev.java.net)
 */
public abstract class BaseAbstractEntity implements PersistentEntity<Long> {

    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return (this.id == null) || (this.id.equals(new Long(0l)));
    }

    @Override
    public Class getEntityClass() {
        return this.getClass();
    }

    @Override
    public abstract boolean equals(Object object);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
