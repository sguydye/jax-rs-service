package org.sguydye.sfservice.dao.impl;

import org.sguydye.sfservice.dao.ConstraintDao;
import org.sguydye.sfservice.model.LogicalConstraint;
import org.springframework.stereotype.Repository;

@Repository
public class ConstraintDaoImpl extends GenericDaoImpl<LogicalConstraint, Integer> implements ConstraintDao {
}
