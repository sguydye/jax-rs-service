package org.sguydye.sfservice.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sguydye.sfservice.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public K save(E entity) {
        return (K) currentSession().save(entity);
        //currentSession().persist(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        currentSession().remove(entity);
    }

    @Override
    public Optional<E> find(K key) {
        return Optional.ofNullable(currentSession().get(daoType, key)) ;
    }

    @Override
    public List<E> getAll() {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<? extends E> cq = criteriaBuilder.createQuery(daoType);
        cq.from(daoType);
        return (List<E>) currentSession().createQuery(cq).getResultList();
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
