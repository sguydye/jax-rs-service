package org.sguydye.sfservice.dao;

import java.util.List;

public interface GenericDao<E, K> {
    public void save(E entity);

    public void update(E entity);

    public void delete(E entity);

    public E find(K key);

    public List<? extends E> getAll();

}
