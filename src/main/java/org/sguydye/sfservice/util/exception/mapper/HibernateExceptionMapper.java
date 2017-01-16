package org.sguydye.sfservice.util.exception.mapper;

import org.hibernate.HibernateException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class HibernateExceptionMapper implements ExceptionMapper<HibernateException> {
    @Override
    public Response toResponse(HibernateException exception) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("Content-type", MediaType.APPLICATION_JSON)
                .entity(new String("PERSISTENCE ERROR"))
                .build();
    }
}
