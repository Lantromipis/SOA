package se.ifmo.ru.utils;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.Message;

public class HttpUtils {

    public static Uni<String> convertEventBusMessageToResponse(Uni<Message<Object>> eventBusMessage){
        return eventBusMessage
                .onItem()
                .transform(Message::body)
                .onItem()
                .castTo(String.class);
    }
}
