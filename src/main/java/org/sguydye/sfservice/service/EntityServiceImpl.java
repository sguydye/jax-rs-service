package org.sguydye.sfservice.service;

import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.UEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.PathParam;

@Service("entityService")
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public UEntity getEntity(@PathParam("id") Integer id) {
        return  entityDao.find(id);
    }

    @Override
    public void postEntity(UEntity entity) {
        entityDao.save(entity);
    }
}
