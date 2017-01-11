package org.sguydye.sfservice.service;

import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
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
