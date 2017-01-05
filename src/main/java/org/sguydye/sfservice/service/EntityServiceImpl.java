package org.sguydye.sfservice.service;

import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("entityService")
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public LogicalEntity getEntity(Integer id) {
        return entityDao.find(id);
    }

    @Override
    public List<LogicalEntity> getAllEntities() {
        return entityDao.getAll();
    }

    @Override
    public void postEntity(LogicalEntity entity) {
        entityDao.save(entity);
    }
}
