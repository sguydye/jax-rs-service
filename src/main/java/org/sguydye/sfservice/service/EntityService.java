package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.LogicalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    public LogicalEntity getEntity(@Min(1) @PathParam("id") Integer id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer postEntity(@Valid LogicalEntity entity);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public LogicalEntity updateEntity(@PathParam("id") Integer id, @Valid LogicalEntity entity);

    @DELETE
    @Path("/{id}")
    public void deleteEntity(@Min(1) @PathParam("id") Integer id);

}
