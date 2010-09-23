/*
 *
 * Copyright (c) 2008 Dan Persa
 * All rights reserved
 *
 */
package org.tomis.mvc.model.eao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * Project: tomis-mvc
 * @author Dan Persa
 * @since 19.03.2008
 */
@Local
public interface BaseEao<BusinessObject extends Serializable> {

    BusinessObject get(Long id);

    List<BusinessObject> get(int startRecord, int maxResults);

    List<BusinessObject> get(String field, Serializable value, int startRecord, int maxResults);

    List<BusinessObject> get(String field, Object value, int startRecord, int maxResults);

    BusinessObject get(String field, Object value);

    List<BusinessObject> get(List<String> fields, List<Serializable> values, int startRecord, int maxResults);

    List<BusinessObject> get(Map<String, Serializable> filters, int startRecord, int maxResults);

    BusinessObject getSingleResult(Map<String, Serializable> filters);

    List<BusinessObject> all();

    BusinessObject save(BusinessObject businessObject);

    BusinessObject update(BusinessObject businessObject);

    void delete(BusinessObject businessObject);

    Long count();

    Long count(String field, Serializable value);

    Long count(List<String> fields, List<Serializable> values);

    Long count(Map<String, Serializable> filters);

    Integer getMaxId();
}
