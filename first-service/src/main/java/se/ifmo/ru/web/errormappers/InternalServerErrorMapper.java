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
public class InternalServerErrorMapper implements ExceptionMapper<Throwable> {
    @Inject
    ResponseUtils responseUtils;

    @Override
    public Response toResponse(Throwable exception) {
        log.error(exception.getMessage(), exception);
        if (StringUtils.isEmpty(exception.getMessage())) {
            return responseUtils.buildResponseWithMessage(
                    Response.Status.INTERNAL_SERVER_ERROR,
                    "Something went wrong..."
            );
        }
        return responseUtils.buildResponseWithMessage(Response.Status.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
