package org.sguydye.sfservice.service;

import org.apache.cxf.common.util.CollectionUtils;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
@Service("entityService")
public class EntityServiceImpl implements EntityService {

    private static final Logger LOG = LoggerFactory.getLogger(EntityServiceImpl.class);

    @Autowired
    private EntityDao entityDao;

    @Override
    public LogicalEntity getEntity(Integer id) {
        LogicalEntity entity = entityDao.find(id);
        LOG.debug("Entity with id: " + id + " was found: " + entity.getName());
        return entity;
    }

    @Override
    public List<LogicalEntity> getAllEntities() {
        return entityDao.getAll();
    }

    @Override
    public Integer postEntity(LogicalEntity entity) {
        return entityDao.save(entity);
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
