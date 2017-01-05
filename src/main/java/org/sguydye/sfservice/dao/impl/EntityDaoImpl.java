package org.sguydye.sfservice.dao.impl;

import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EntityDaoImpl extends GenericDaoImpl<LogicalEntity, Integer> implements EntityDao {
}
