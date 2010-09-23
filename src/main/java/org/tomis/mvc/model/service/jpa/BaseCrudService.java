package org.tomis.mvc.model.service.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.tomis.mvc.model.entity.PersistentEntity;

/**
 * Project: tomis-mvc
 * @since 20.09.2009
 * @author Dan Persa
 */
public interface BaseCrudService<BusinessObject extends PersistentEntity> {

    List<BusinessObject> all();

    BusinessObject get(Long id);

    List<BusinessObject> get(final int firstResult, final int maxResults);

    List<BusinessObject> get(String field, Serializable value, int startRecord, int maxResults);

    List<BusinessObject> get(String field, Object value, int startRecord, int maxResults);

    BusinessObject get(String field, Object value);

    List<BusinessObject> get(List<String> fields, List<Serializable> values, int startRecord, int maxResults);

    List<BusinessObject> get(Map<String, Serializable> filters, int startRecord, int maxResults);

    BusinessObject save(BusinessObject businessObject) throws Exception;

    void delete(BusinessObject businessObject);

    Long count();

    Long count(String field, Serializable value);

    Long count(List<String> fields, List<Serializable> values);

    Long count(Map<String, Serializable> filters);
}
