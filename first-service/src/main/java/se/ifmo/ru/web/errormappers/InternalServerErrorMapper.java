package se.ifmo.ru.web.errormappers;

import se.ifmo.ru.util.ResponseUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorMapper implements ExceptionMapper<Throwable> {
    @Inject
    ResponseUtils responseUtils;

    @Override
    public Response toResponse(Throwable exception) {
        return responseUtils.buildResponseWithMessage(Response.Status.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
