package org.sguydye.sfservice.util.exception.mapper;

import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationExceptionMapper.class);

    private boolean addMessageToResponse;

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        Response.Status status = Response.Status.BAD_REQUEST;
        StringBuilder responseBody = addMessageToResponse ? new StringBuilder() : null;

        if (responseBody != null) {
            exception.getConstraintViolations().forEach(constraintViolation -> {
                responseBody
                        .append(getMessage(constraintViolation))
                        .append("\n");
                LOG.warn("Value {} of {}.{} : {}", constraintViolation.getInvalidValue(), constraintViolation.getRootBeanClass(),
                        constraintViolation.getPropertyPath(), constraintViolation.getMessage());
            });
        }

        if (exception instanceof ResponseConstraintViolationException) {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }

        return Response
                .status(status)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(responseBody)
                .build();
    }

    public void setAddMessageToResponse(boolean addMessageToResponse) {
        this.addMessageToResponse = addMessageToResponse;
    }

    private String getMessage(ConstraintViolation<?> violation) {
        return "Value "
                + (violation.getInvalidValue() != null ? "'" + violation.getInvalidValue().toString() + "'" : "(null)")
                + " of " + violation.getRootBeanClass().getSimpleName()
                + "." + violation.getPropertyPath()
                + ": " + violation.getMessage();
    }
}
