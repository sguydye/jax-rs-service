package org.sguydye.sfservice.util.filters;

import org.sguydye.sfservice.model.LogicalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NameBinding;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class CustomEntityResponseFilter implements ContainerResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CustomEntityResponseFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        if (responseContext.getEntity() == null) {

            responseContext.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            LOG.debug("Response filter invoked after non-existing entity called. New status: {}", responseContext.getStatus());
        }

    }
}
