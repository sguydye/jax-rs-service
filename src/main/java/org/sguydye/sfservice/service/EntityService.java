package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.UEntity;
import javax.ws.rs.*;


@Path("/entity")
@Produces("application/json")
public interface EntityService {

    @GET
    @Path("/{id}")
    public UEntity getEntity(@PathParam("id") Integer id);

    @POST
    @Consumes("application/json")
    @Path("/")
    public void postEntity(UEntity entity);
}
