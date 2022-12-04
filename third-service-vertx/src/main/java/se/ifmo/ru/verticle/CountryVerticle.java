package se.ifmo.ru.verticle;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.mapper.CountryMapper;
import se.ifmo.ru.mapper.HouseMapper;
import se.ifmo.ru.model.storage.CountryEntity;
import se.ifmo.ru.model.storage.HouseEntity;
import se.ifmo.ru.utils.XmlUtils;

@Slf4j
public class CountryVerticle extends AbstractVerticle {
    @Override
    public Uni<Void> asyncStart() {
        vertx.eventBus().consumer("service.country.getAll", msg -> {
            getAll(msg);
        });

        vertx.eventBus().consumer("service.country.getHousesInCountry", msg -> {
            getAllHousesInCountry(msg);
        });

        return Uni.createFrom().voidItem();
    }

    private void getAll(Message<Object> msg) {
        DatabaseVerticle.getSessionFactory()
                .withSession(session -> session
                        .createQuery("from CountryEntity ", CountryEntity.class)
                        .getResultList())
                .onItem()
                .transform(CountryMapper::from)
                .onItem()
                .transform(XmlUtils::objectToXmlString)
                .subscribe()
                .with(msg::reply);
    }

    private void getAllHousesInCountry(Message<Object> msg) {
        DatabaseVerticle.getSessionFactory()
                .withSession(session -> session
                        .createQuery("from HouseEntity where country.id = ?1", HouseEntity.class)
                        .setParameter(1, Long.parseLong(msg.body().toString()))
                        .getResultList())
                .onItem()
                .transform(HouseMapper::from)
                .onItem()
                .transform(XmlUtils::objectToXmlString)
                .subscribe()
                .with(msg::reply);
    }
}
