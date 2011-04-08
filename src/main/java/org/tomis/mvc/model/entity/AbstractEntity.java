package org.tomis.mvc.model.entity;

/**
 * 
 * @author Dr. Spock (spock at dev.java.net)
 */
public abstract class AbstractEntity implements PersistentEntity<Long> {

    protected Long id;
    protected Long version;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean isNew() {
        return (this.id == null);
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
