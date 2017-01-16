package org.sguydye.sfservice.util.exception.mapper;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ClientExceptionMapper implements ExceptionMapper<ClientErrorException> {
    @Override
    public Response toResponse(ClientErrorException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .header("Content-type", MediaType.APPLICATION_JSON)
                .entity(new String("NOT FOUND"))
                .build();
    }
}
