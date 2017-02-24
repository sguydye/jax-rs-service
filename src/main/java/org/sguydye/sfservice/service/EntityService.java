package org.sguydye.sfservice.service;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.sguydye.sfservice.model.LogicalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/*
@CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true,
        maxAge = 1,
        allowHeaders = {
                "X-custom-1", "X-custom-2"
        },
        exposeHeaders = {
                "X-custom-3", "X-custom-4"
        }
)*/
@Path("/entity")
@Produces(MediaType.APPLICATION_JSON)
public interface EntityService {

    //@CrossOriginResourceSharing(allowAllOrigins = true)
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
