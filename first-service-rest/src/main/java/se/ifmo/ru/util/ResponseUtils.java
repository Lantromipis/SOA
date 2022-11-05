package se.ifmo.ru.util;

import se.ifmo.ru.web.model.Error;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.time.Instant;

@ApplicationScoped
public class ResponseUtils {
    public Response buildResponseWithMessage(Response.Status status, String message) {
        return Response
                .status(status)
                .entity(Error
                        .builder()
                        .message(message)
                        .code(String.valueOf(status.getStatusCode()))
                        .date(Instant.now().toString())
                        .build()
                )
                .build();
    }
}
