package org.sguydye.sfservice.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E, K> {
    public K save(E entity);

    public void update(E entity);

    public void delete(E entity);

    public Optional<E> find(K key);

    public List<E> getAll();

}
