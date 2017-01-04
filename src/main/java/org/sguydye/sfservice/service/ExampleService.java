package org.sguydye.sfservice.service;

import org.sguydye.sfservice.model.ExampleModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/example")
@Produces("application/json")
public interface ExampleService {

    @GET
    @Path("/{id}")
    public ExampleModel getEntity(@PathParam("id") Integer id);


}
