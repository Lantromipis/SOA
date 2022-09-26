package se.ifmo.ru.web.errormappers;

import se.ifmo.ru.util.ResponseUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Inject
    ResponseUtils responseUtils;

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, exception.getMessage());
    }
}
