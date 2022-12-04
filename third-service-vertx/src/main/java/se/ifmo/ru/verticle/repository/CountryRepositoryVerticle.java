package se.ifmo.ru.verticle.repository;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import se.ifmo.ru.model.storage.CountryEntity;
import se.ifmo.ru.verticle.core.DatabaseVerticle;

import java.util.List;

@Slf4j
public class CountryRepositoryVerticle extends AbstractVerticle {

    @Override
    public Uni<Void> asyncStart() {
        vertx.eventBus().consumer("repository.country.listAll", msg -> {
            log.info("******************************************* Countries repository received *********************");
            msg.reply(listAll());
        });

        return Uni.createFrom().voidItem();
    }

    private Uni<List<CountryEntity>> listAll() {
        return DatabaseVerticle.getSessionFactory()
                .withSession(session -> session
                        .createQuery("from CountryEntity ", CountryEntity.class)
                        .getResultList());
    }
}
