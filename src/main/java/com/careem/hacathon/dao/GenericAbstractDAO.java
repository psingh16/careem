package com.careem.hacathon.dao;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by kumari.singh on 25/02/17.
 */
public class GenericAbstractDAO <T> extends AbstractDAO<T> {

    private final String simpleName;

    private ListeningExecutorService service;

    public GenericAbstractDAO(SessionFactory sessionFactory, String simpleName) {
        super(sessionFactory);
        this.simpleName = simpleName;
        service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));
    }

    public T create(T obj) {
        return persist(obj);
    }

    public T update(T obj) {
        return persist(obj);
    }

    public void delete(T obj) {
        currentSession().delete(obj);
    }

    public List<T> findAll() {
        return list(namedQuery(simpleName + ".findAll"));
    }

    public List<T> findUsingQuery(String queryString, String id, Object obj) {
        final org.hibernate.Query query = namedQuery(queryString).setParameter(id, obj);
        List<T> resultList = query.list();
        return resultList;
    }
    public List<T> findUsingQuery(String queryString, String id, Object obj,int limit) {
        final org.hibernate.Query query = namedQuery(queryString).setParameter(id, obj);
        query.setMaxResults(limit);
        List<T> resultList = query.list();
        return resultList;
    }

    public List<T> findUsingQuery(String queryString, Map<String, Object> params) {
        final org.hibernate.Query query = namedQuery(queryString);
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<T> resultList = query.list();
        return resultList;
    }

    public List<T> findUsingQuery(String queryString, Map<String, Object> params, int lowerBound, int limit) {
        final org.hibernate.Query query = namedQuery(queryString);
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(lowerBound);
        query.setMaxResults(limit);
        List<T> resultList = query.list();
        return resultList;
    }

    public List findUsingCustomQuery(String queryString, Map<String, Object> params) {
        Query query = currentSession().createSQLQuery(queryString);
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getValue() instanceof List) {
                query.setParameterList(entry.getKey(), (Collection) entry.getValue());

            } else {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        List resultList = query.list();
        return resultList;
    }


    public List<T> findUsingQuery(String queryString, Map<String, Object> params, int limit) {
        final org.hibernate.Query query = namedQuery(queryString);
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setMaxResults(limit);
        List<T> resultList = query.list();
        return resultList;
    }
}

