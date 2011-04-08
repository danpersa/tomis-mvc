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
        System.out.println("BaseCrudServiceBean: get(int, int)");
        return getEao().get(firstResult, maxResults);
    }

    @Override
    public List<BusinessObject> get(String field, Serializable value, int startRecord, int maxResults) {
        System.out.println("BaseCrudServiceBean: fdasfadfa");
        logger.info("field: " + field + " value: " + value + " startRecord: " + startRecord + " maxResults:" + maxResults);
        // logger.info("field: " + field + " value: " + value.getClass() +
        // " startRecord: " + startRecord + " maxResults:" + maxResults);
        return getEao().get(field, value, startRecord, maxResults);
    }

    @Override
    public List<BusinessObject> get(String field, Object value, int startRecord, int maxResults) {
        System.out.println("BaseCrudServiceBean: aabbbaadfa");
        return getEao().get(field, value, startRecord, maxResults);
    }

    @Override
    public BusinessObject get(String field, Object value) {
        System.out.println("BaseCrudServiceBean: get(String, Object)");
        return getEao().get(field, value);
    }

    @Override
    public List<BusinessObject> get(List<String> fields, List<Serializable> values, int startRecord, int maxResults) {
        System.out.println("BaseCrudServiceBean: fdasfadfaafddafdasdfasdfa");
        return getEao().get(fields, values, startRecord, maxResults);
    }

    @Override
    public List<BusinessObject> get(Map<String, Serializable> filters, int startRecord, int maxResults) {
        System.out.println("BaseCrudServiceBean: fdasfadfaaaadfeewrf");
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
            return getEao().create(businessObject);
        }
        logger.info("update");
        return getEao().update(businessObject);
    }

    @Override
    public BusinessObject create(BusinessObject businessObject) throws Exception {
        logger.info("create");
        return getEao().create(businessObject);
    }

    @Override
    public BusinessObject update(BusinessObject businessObject) throws Exception {
        logger.info("update");
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

    @Override
    public void flush() {
        getEao().flush();
    }

    protected abstract BaseEao<BusinessObject> getEao();
}
