package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.LogicalEntity;
import javax.ws.rs.*;
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
}
