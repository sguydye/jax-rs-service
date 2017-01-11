package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.LogicalEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/entity")
@Produces("application/json")
public interface EntityService {

    @GET
    public List<LogicalEntity> getAllEntities();

    @GET
    @Path("/{id}")
    public LogicalEntity getEntity(@PathParam("id") Integer id);

    @POST
    @Consumes("application/json")
    public void postEntity(LogicalEntity entity);

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateEntity(@PathParam("id") Integer id, LogicalEntity entity);

    @DELETE
    @Path("/{id}")
    public void deleteEntity(@PathParam("id") Integer id);

}
