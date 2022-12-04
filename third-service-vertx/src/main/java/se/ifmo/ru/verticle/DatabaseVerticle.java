package se.ifmo.ru.verticle;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.persistence.Persistence;

@Slf4j
public class DatabaseVerticle extends AbstractVerticle {

    @Getter
    private static Mutiny.SessionFactory sessionFactory;

    @Override
    public Uni<Void> asyncStart() {
        Uni<Void> startHibernate = Uni.createFrom().deferred(() -> {
            sessionFactory = Persistence.createEntityManagerFactory("postgres").unwrap(Mutiny.SessionFactory.class);
            return Uni.createFrom().voidItem();
        });

        startHibernate = vertx.executeBlocking(startHibernate)
                .onItem().invoke(() -> log.info("Hibernate Reactive is ready"));


        return Uni.combine().all().unis(startHibernate).discardItems();
    }
}
