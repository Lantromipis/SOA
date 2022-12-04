package se.ifmo.ru;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.verticle.core.DatabaseVerticle;
import se.ifmo.ru.verticle.core.HttpVerticle;


@Slf4j
public class MainVerticle extends AbstractVerticle {
    @Override
    public Uni<Void> asyncStart() {
        Uni<String> deployDatabaseVerticelUni = vertx.deployVerticle(new DatabaseVerticle());
        Uni<String> deployHttpVerticelUni = vertx.deployVerticle(new HttpVerticle());

        return Uni.combine().all().unis(deployDatabaseVerticelUni, deployHttpVerticelUni).discardItems();
    }
}
