package org.sguydye.sfservice.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sguydye.sfservice.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericDaoImpl<E,K extends Serializable> implements GenericDao<E, K> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E find(K key) {
        return (E) currentSession().find(daoType, key);
    }
    //Rewrite!!
    @Override
    public List<E> getAll() {
        return currentSession().createCriteria(daoType).list();
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
