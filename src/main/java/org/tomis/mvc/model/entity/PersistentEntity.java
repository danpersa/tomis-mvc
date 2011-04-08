package org.tomis.mvc.model.entity;

import java.io.Serializable;

/**
 *
 * @author Dr. Spock (spock at dev.java.net)
 */
public interface PersistentEntity<PK extends Serializable> extends Serializable {

    PK getId();

    void setId(PK key);

    void setVersion(Long version);

    Long getVersion();

    boolean isNew();

    Class getEntityClass();

    @Override
    public boolean equals(Object object);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
