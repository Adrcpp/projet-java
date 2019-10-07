package com.persistence.dao.impl;

import com.persistence.dao.IDao;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public abstract class AbstractDao<T> implements IDao<T> {

    // @PersistenceUnit
    // protected EntityManagerFactory emf;
    
    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDao(Class<T> myClass) {
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public T create(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }

    @Override
    public boolean delete(T t) {
        entityManager.remove(entityManager.merge(t));
        return true;
    }
}
