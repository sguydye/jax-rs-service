package org.sguydye.sfservice.util.exception.mapper;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class HibernateExceptionMapper implements ExceptionMapper<HibernateException> {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateExceptionMapper.class);

    @Override
    public Response toResponse(HibernateException exception) {

        LOG.warn("Unexpected hibernate exception occured. Message:{}", exception.getMessage());
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("Content-type", MediaType.APPLICATION_JSON)
                .entity(exception.getMessage())
                .build();
    }
}
