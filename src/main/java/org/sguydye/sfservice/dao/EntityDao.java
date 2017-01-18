package org.sguydye.sfservice.dao;

import org.sguydye.sfservice.model.LogicalEntity;

import java.util.List;

public interface EntityDao extends GenericDao<LogicalEntity, Integer> {
    public List<LogicalEntity> findByName(String name);
}
