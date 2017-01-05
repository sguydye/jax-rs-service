package org.sguydye.sfservice.dao.impl;

import org.sguydye.sfservice.dao.FieldDao;
import org.sguydye.sfservice.model.LogicalField;
import org.springframework.stereotype.Repository;

@Repository
public class FieldDaoImpl extends GenericDaoImpl<LogicalField, Integer> implements FieldDao {
}
