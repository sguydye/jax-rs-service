package org.sguydye.sfservice.util.exception.mapper;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ServerExceptionMapper implements ExceptionMapper<ServerErrorException> {
    @Override
    public Response toResponse(ServerErrorException exception) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("Content-type", MediaType.APPLICATION_JSON)
                .entity(new String("INTERNAL SERVER ERROR"))
                .build();
    }
}
