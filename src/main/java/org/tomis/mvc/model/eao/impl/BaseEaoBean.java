package org.tomis.mvc.model.eao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tomis.mvc.model.eao.BaseEao;
import org.tomis.mvc.model.entity.PersistentEntity;

/**
 * Project: tomis-mvc
 * @since 20.09.2009
 * @author Dan Persa
 */
public abstract class BaseEaoBean<BusinessObject extends PersistentEntity> implements BaseEao<BusinessObject> {

    private static Logger logger = LoggerFactory.getLogger(BaseEaoBean.class);
    private Class<BusinessObject> entityClass;

    protected abstract EntityManager getEntityManager();

    public BaseEaoBean(Class<BusinessObject> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public BusinessObject get(Long id) {
        System.out.println("BaseEaoBean: get(Long)");
        return getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public List<BusinessObject> get(int startRecord, int maxResults) {
        System.out.println("BaseEaoBean: get(int, int)");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        Query q = getEntityManager().createQuery(cq);
        if (startRecord >= 0) {
            q.setFirstResult(startRecord);
        }
        if (maxResults != 0) {
            q.setMaxResults(maxResults);
        }
        return q.getResultList();
    }

    @Override
    public List<BusinessObject> get(String field, Serializable value, int startRecord, int maxResults) {
        System.out.println("BaseEaoBean: get(String, Serializable, int, int)");
        return get(field, (Object) value, startRecord, maxResults);
    }

    @Override
    public List<BusinessObject> get(String field, Object value, int startRecord, int maxResults) {
        System.out.println("BaseEaoBean: get(String, Object, int, int)");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        logger.info("getEntityClass: " + getEntityClass());
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        logger.info("cq: " + cq.toString());
        Root<BusinessObject> businessObject = cq.from((Class<BusinessObject>) getEntityClass());
        if (value != null && field != null) {
            cq = cq.where(cb.equal(businessObject.get(field), value));
        }
        Query q = getEntityManager().createQuery(cq);
        if (startRecord != 0) {
            q.setFirstResult(startRecord);
        }
        if (maxResults != 0) {
            q.setMaxResults(maxResults);
        }
        return q.getResultList();
    }

    @Override
    public BusinessObject get(String field, Object value) {
        System.out.println("BaseEaoBean: get(String, Object)");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (value != null && field != null) {
            cq = cq.where(cb.equal(businessObject.get(field), value));
        }
        Query q = getEntityManager().createQuery(cq);
        return (BusinessObject) q.getSingleResult();
    }

    @Override
    public List<BusinessObject> get(List<String> fields, List<Serializable> values, int startRecord, int maxResults) {
        System.out.println("BaseEaoBean: get(List<String>, List<Serializable>, int, int)");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (values != null && fields != null && !fields.isEmpty() && !values.isEmpty()) {
            if (fields.size() != values.size()) {
                throw new RuntimeException("fields and values sizes must be equal!!");
            }
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (int i = 0; i < fields.size(); ++i) {
                if (fields.get(i) != null && values.get(i) != null) {
                    predicates.add(cb.equal(businessObject.get(fields.get(i)), values.get(i)));
                }
            }
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[]{}));
            }
        }
        Query q = getEntityManager().createQuery(cq);
        if (startRecord != 0) {
            q.setFirstResult(startRecord);
        }
        if (maxResults != 0) {
            q.setMaxResults(maxResults);
        }
        return q.getResultList();
    }

    @Override
    public List<BusinessObject> get(Map<String, Serializable> filters, int startRecord, int maxResults) {
        System.out.println("BaseEaoBean: get(Map<String, Serializable>, int, int)");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (filters != null && !filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (String fieldName : filters.keySet()) {
                if (filters.get(fieldName) != null) {
                    predicates.add(cb.equal(businessObject.get(fieldName), filters.get(fieldName)));
                }
            }
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[]{}));
            }
        }
        Query q = getEntityManager().createQuery(cq);
        if (startRecord != 0) {
            q.setFirstResult(startRecord);
        }
        if (maxResults != 0) {
            q.setMaxResults(maxResults);
        }
        return q.getResultList();
    }

    @Override
    public BusinessObject getSingleResult(Map<String, Serializable> filters) {
        System.out.println("BaseEaoBean: getSingleResult(Map<String, Serializable>)");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (filters != null && !filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (String fieldName : filters.keySet()) {
                if (filters.get(fieldName) != null) {
                    predicates.add(cb.equal(businessObject.get(fieldName), filters.get(fieldName)));
                }
            }
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[]{}));
            }
        }
        Query q = getEntityManager().createQuery(cq);

        return (BusinessObject) q.getSingleResult();
    }

    @Override
    public List<BusinessObject> all() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BusinessObject> cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    @Override
    public BusinessObject create(BusinessObject businessObject) {
        getEntityManager().persist(businessObject);
        return businessObject;
    }

    @Override
    public BusinessObject update(BusinessObject businessObject) {
        getEntityManager().merge(businessObject);
        return businessObject;
    }

    @Override
    public void delete(BusinessObject businessObject) {
        getEntityManager().remove(getEntityManager().merge(businessObject));
    }

    @Override
    public Long count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> r = cq.from(getEntityClass());
        cq.select(cb.count(r));
        Query q = getEntityManager().createQuery(cq);
        return (Long) q.getSingleResult();
    }

    @Override
    public Long count(String field, Serializable value) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (value != null && field != null) {
            cq.where(cb.equal(businessObject.get(field), value));
        }
        cq.select(cb.count(businessObject));
        Query q = getEntityManager().createQuery(cq);
        return (Long) q.getSingleResult();
    }

    @Override
    public Long count(List<String> fields, List<Serializable> values) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (values != null && fields != null && !fields.isEmpty() && !values.isEmpty()) {
            if (fields.size() != values.size()) {
                throw new RuntimeException("fields and values sizes must be equal!!");
            }
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (int i = 0; i < fields.size(); ++i) {
                if (fields.get(i) != null && values.get(i) != null) {
                    predicates.add(cb.equal(businessObject.get(fields.get(i)), values.get(i)));
                }
            }
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[]{}));
            }
        }
        cq.select(cb.count(businessObject));
        Query q = getEntityManager().createQuery(cq);
        return (Long) q.getSingleResult();
    }

    @Override
    public Long count(Map<String, Serializable> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(getEntityClass());
        Root<BusinessObject> businessObject = cq.from(getEntityClass());
        if (filters != null && !filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (String fieldName : filters.keySet()) {
                if (filters.get(fieldName) != null) {
                    predicates.add(cb.equal(businessObject.get(fieldName), filters.get(fieldName)));
                }
            }
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[]{}));
            }
        }
        cq.select(cb.count(businessObject));
        Query q = getEntityManager().createQuery(cq);
        return (Long) q.getSingleResult();
    }

    @Override
    public Integer getMaxId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void flush() {
        getEntityManager().flush();
    }

    public Class<BusinessObject> getEntityClass() {
        return entityClass;
    }
}
