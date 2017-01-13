package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.LogicalEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/entity")
@Produces(MediaType.APPLICATION_JSON)
public interface EntityService {

    @GET
    public List<LogicalEntity> getAllEntities();

    @GET
    @Path("/{id}")
    public LogicalEntity getEntity(@PathParam("id") Integer id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postEntity(LogicalEntity entity);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntity(@PathParam("id") Integer id, LogicalEntity entity);

    @DELETE
    @Path("/{id}")
    public void deleteEntity(@PathParam("id") Integer id);

}
