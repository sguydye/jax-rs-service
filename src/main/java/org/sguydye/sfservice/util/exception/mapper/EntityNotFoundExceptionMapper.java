package org.sguydye.sfservice.util.exception.mapper;

import org.sguydye.sfservice.util.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationExceptionMapper.class);

    @Override
    public Response toResponse(EntityNotFoundException exception) {

        return Response
                .status(Response.Status.NOT_FOUND)
                .header("content-type", MediaType.APPLICATION_JSON)
                .build();
    }

}
