package org.sguydye.sfservice.service;

import org.apache.cxf.common.util.CollectionUtils;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
@Service("entityService")
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public LogicalEntity getEntity(Integer id) {
        LogicalEntity entity = entityDao.find(id);
        return entity;
    }

    @Override
    public List<LogicalEntity> getAllEntities() {
        return entityDao.getAll();
    }

    @Override
    public void postEntity(LogicalEntity entity) {

        if (!CollectionUtils.isEmpty(entity.getFields())) {
            entity.getFields().forEach(logicalField -> {
                logicalField.setEntity(entity);
            });
        }
        if (!CollectionUtils.isEmpty(entity.getConstraints())) {
            entity.getConstraints().forEach(logicalConstraint -> {
                logicalConstraint.setEntity(entity);
            });
        }
        entityDao.save(entity);
    }

    @Override
    public Response updateEntity(Integer id, LogicalEntity entity) {
        entity.setId(id);
        entityDao.update(entity);
        return Response.status(200).entity(entityDao.find(id)).build();
    }

    @Override
    public void deleteEntity(Integer id) {
        LogicalEntity entity = entityDao.find(id);
        entityDao.delete(entity);
    }

}
