package org.sguydye.sfservice.service;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.util.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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
        LogicalEntity entity = entityDao.find(id).orElseThrow(EntityNotFoundException::new);
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
    public LogicalEntity updateEntity(Integer id, LogicalEntity entity) {
        entity.setId(id);
        entityDao.update(entity);
        return entityDao.find(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteEntity(Integer id) {
        LogicalEntity entity = entityDao.find(id).orElseThrow(EntityNotFoundException::new);
        entityDao.delete(entity);
    }

/*
    @OPTIONS
    @Path("/")
    @LocalPreflight
    public Response options() {
        LOG.debug("INVOKED");
        return Response.status(Response.Status.NOT_FOUND)
                .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "DELETE")
                .build();
    }
*/
}
