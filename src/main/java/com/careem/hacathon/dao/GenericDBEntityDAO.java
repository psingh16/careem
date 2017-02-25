package com.careem.hacathon.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by kumari.singh on 26/02/17.
 */
@Repository
public class GenericDBEntityDAO<T> {

        @PersistenceContext(unitName = "ims-dpm")
        private EntityManager manager;

        public GenericDBEntityDAO() {

        }
        @Transactional
        public T findById(Object stateId, Class<T> clazz) {
            return manager.find(clazz, stateId);
        }

        @Transactional
        public T create(T t) {
            manager.persist(t);
            manager.flush();
            manager.detach(t);
            return t;
        }

        public List<EntityGraph<? super T>> getEntityGraphFor(Class<T> clazz){
            return manager.getEntityGraphs(clazz);
        }

        public boolean isManaged(T t){
            return manager.contains(t);
        }

        @Transactional
        public void delete(T t) {
            manager.remove(t);
            manager.flush();
        }

        @SuppressWarnings("unchecked")
        @Transactional
        public List<T> findAll(String q) {
            Query query = manager.createNamedQuery(q);
            List<T> resultList = query.getResultList();
            return resultList;
        }

        @Transactional
        public T update(T t) {
            T t1 = manager.merge(t);
            manager.flush();
            manager.detach(t1);
            return t1;
        }

        @SuppressWarnings("unchecked")
        @Transactional
        public List<T> findUsingQuery(String queryString, String id, Object obj) {
            Query query =  manager.createNamedQuery(queryString).setParameter(id, obj);
            List<T> resultList = query.getResultList();
            return resultList;
        }

        @Transactional
        public List<T> findUsingQuery(String queryString, String id, Object obj, int limit) {
            Query query =  manager.createNamedQuery(queryString).setParameter(id, obj);
            query.setMaxResults(limit);
            List<T> resultList = query.getResultList();
            return resultList;
        }

        @Transactional
        public List<T> findUsingQuery(String queryString, Map<String, Object> params) {
            Query query =  manager.createNamedQuery(queryString);
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            List<T> resultList = query.getResultList();
            return resultList;
        }

        @Transactional
        public List<T> findUsingQuery(String queryString, Map<String, Object> params, int limit) {
            Query query =  manager.createNamedQuery(queryString);
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            query.setMaxResults(limit);
            List<T> resultList = query.getResultList();
            return resultList;
        }

        @Transactional
        public List<T> findUsingQuery(String queryString, Map<String, Object> params, Integer firstResult, Integer maxResult) {
            Query query =  manager.createNamedQuery(queryString);
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            if(firstResult != null) {
                query.setFirstResult(firstResult);
            }
            if(maxResult != null) {
                query.setMaxResults(maxResult);
            }

            return query.getResultList();
        }

        @Transactional
        public List<T> findUsingConstructedQuery(String queryString, Map<String, Object> params, Integer firstResult, Integer maxResult) {
            Query query =  manager.createQuery(queryString);
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            if(firstResult != null) {
                query.setFirstResult(firstResult);
            }
            if(maxResult != null) {
                query.setMaxResults(maxResult);
            }

            return query.getResultList();
        }

        @Transactional
        public List<T> findUsingConstructedNativeQuery(String queryString, Map<String, Object> params, Integer firstResult, Integer maxResult) {
            Query query =  manager.createNativeQuery(queryString);
            for(Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            if(firstResult != null) {
                query.setFirstResult(firstResult);
            }
            if(maxResult != null) {
                query.setMaxResults(maxResult);
            }

            return query.getResultList();
        }
}

