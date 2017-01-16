package org.sguydye.sfservice.util.exception.mapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(new String("BAD REQUEST"))
                .build();
    }
}
