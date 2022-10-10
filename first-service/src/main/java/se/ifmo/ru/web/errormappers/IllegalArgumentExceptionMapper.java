package se.ifmo.ru.web.errormappers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import se.ifmo.ru.util.ResponseUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Inject
    ResponseUtils responseUtils;

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        if (StringUtils.isEmpty(exception.getMessage())) {
            return responseUtils.buildResponseWithMessage(
                    Response.Status.BAD_REQUEST,
                    "Bad request"
            );
        }
        return responseUtils.buildResponseWithMessage(Response.Status.BAD_REQUEST, exception.getMessage());
    }
}
