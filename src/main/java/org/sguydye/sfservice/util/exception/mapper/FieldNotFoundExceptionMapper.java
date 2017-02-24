package org.sguydye.sfservice.util.exception.mapper;

import org.sguydye.sfservice.util.exception.FieldNotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FieldNotFoundExceptionMapper implements ExceptionMapper<FieldNotFoundException> {
    @Override
    public Response toResponse(FieldNotFoundException e) {

        return Response
                .status(Response.Status.BAD_REQUEST)
                .header("content-type", MediaType.APPLICATION_JSON)
                .build();
    }
}
