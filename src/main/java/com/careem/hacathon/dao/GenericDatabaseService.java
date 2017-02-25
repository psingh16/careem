package com.careem.hacathon.dao;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by kumari.singh on 26/02/17.
 */
@org.springframework.stereotype.Service
public class GenericDatabaseService <T> implements Serializable {

    @Autowired
    GenericDBEntityDAO<T> genericDBEntityDAO;

        private ListeningExecutorService listeningExecutorService;

        public GenericDatabaseService(){
         listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));
        }

        public T create(T t) {
         return genericDBEntityDAO.create(t);
        }


        public T update(T t) {
                return genericDBEntityDAO.update(t);
        }

        public boolean isManaged(T t){
                return genericDBEntityDAO.isManaged(t);
        }





public void delete(T t) {
        genericDBEntityDAO.delete(t);
        }

public List<T> findUsingQuery(String queryString, String id, Object obj){
        return genericDBEntityDAO.findUsingQuery(queryString,id, obj);
        }

public List<T> findUsingQuery(String queryString, String id, Object obj, int limit){
        return genericDBEntityDAO.findUsingQuery(queryString,id, obj, limit);
        }



public List<T> findUsingQuery(String queryString, Map<String, Object> params) {
        return genericDBEntityDAO.findUsingQuery(queryString, params);
        }

public List<T> findUsingQuery(String queryString, Map<String, Object> params, int limit) {
        return genericDBEntityDAO.findUsingQuery(queryString, params, limit);
        }

public List<T> findUsingQuery(String queryString, Map<String, Object> params, Integer firstResult, Integer maxResult) {
        return genericDBEntityDAO.findUsingQuery(queryString, params, firstResult, maxResult);
        }

public List<T> findUsingConstructedQuery(String queryString, Map<String, Object> params, Integer firstResult, Integer maxResult) {
        return genericDBEntityDAO.findUsingConstructedQuery(queryString, params, firstResult, maxResult);
        }

public List<T> findUsingConstructedNativeQuery(String queryString, Map<String, Object> params, Integer firstResult, Integer maxResult) {
        return genericDBEntityDAO.findUsingConstructedNativeQuery(queryString, params, firstResult, maxResult);
        }
        }

