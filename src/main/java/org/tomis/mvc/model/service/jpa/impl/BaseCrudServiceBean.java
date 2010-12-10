package org.tomis.mvc.model.service.jpa.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomis.mvc.model.eao.BaseEao;
import org.tomis.mvc.model.entity.PersistentEntity;
import org.tomis.mvc.model.service.jpa.BaseCrudService;

/**
 * Project: tomis-mvc
 * @since 08.09.2009
 * @author Dan Persa
 */
public abstract class BaseCrudServiceBean<BusinessObject extends PersistentEntity> implements BaseCrudService<BusinessObject> {

    private static Logger logger = LoggerFactory.getLogger(BaseCrudServiceBean.class);

    @Override
    public List<BusinessObject> all() {
        return getEao().all();
    }

    @Override
    public BusinessObject get(Long id) {
        logger.info("[get]: id: " + id);
        return getEao().get(id);
    }

    @Override
    public List<BusinessObject> get(int firstResult, int maxResults) {
        if (firstResult == 0) {
            firstResult = 1;
        }
        maxResults = 100;
        return getEao().get(firstResult, maxResults);
    }

    @Override
    public List<BusinessObject> get(String field, Serializable value, int startRecord, int maxResults) {
        logger.info("field: " + field + " value: " + value + " startRecord: " + startRecord + " maxResults:" + maxResults);
        // logger.info("field: " + field + " value: " + value.getClass() +
        // " startRecord: " + startRecord + " maxResults:" + maxResults);
        return getEao().get(field, value, startRecord, maxResults);
    }

    // @Override
    // public List<BusinessObject> get(String field, Object value, int
    // startRecord, int maxResults) {
    // return getEao().get(field, value, startRecord, maxResults);
    // }
    @Override
    public BusinessObject get(String field, Object value) {
        return getEao().get(field, value);
    }

    // @Override
    // public List<BusinessObject> get(List<String> fields, List<Serializable>
    // values, int startRecord, int maxResults) {
    // return getEao().get(fields, values, startRecord, maxResults);
    // }
    @Override
    public List<BusinessObject> get(Map<String, Serializable> filters, int startRecord, int maxResults) {
        if (startRecord == 0) {
            startRecord = 1;
        }
        maxResults = 100;
        return getEao().get(filters, startRecord, maxResults);
    }

    @Override
    public BusinessObject save(BusinessObject businessObject) throws Exception {
        logger.info("save " + businessObject);
        if (businessObject.isNew()) {
            logger.info("Save new");
            return getEao().save(businessObject);
        }
        logger.info("Update");
        return getEao().update(businessObject);
    }

    @Override
    public void delete(BusinessObject businessObject) {
        getEao().delete(businessObject);
    }

    @Override
    public Long count() {
        return getEao().count();
    }

    @Override
    public Long count(String field, Serializable value) {
        return getEao().count(field, value);
    }

    @Override
    public Long count(List<String> fields, List<Serializable> values) {
        return getEao().count(fields, values);
    }

    @Override
    public Long count(Map<String, Serializable> filters) {
        return getEao().count(filters);
    }

    protected abstract BaseEao<BusinessObject> getEao();
}
