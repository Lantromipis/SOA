package se.ifmo.ru.verticle.service;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.mapper.CountryMapper;
import se.ifmo.ru.mapper.HouseMapper;
import se.ifmo.ru.model.storage.CountryEntity;
import se.ifmo.ru.model.storage.HouseEntity;
import se.ifmo.ru.model.web.CountryListResponseDto;
import se.ifmo.ru.model.web.CountryResponseDto;
import se.ifmo.ru.utils.XmlUtils;
import se.ifmo.ru.verticle.core.DatabaseVerticle;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CountryVerticle extends AbstractVerticle {
    @Override
    public Uni<Void> asyncStart() {
        vertx.eventBus().consumer("service.country.getAll", msg -> {
            getAll(msg);
        });

        vertx.eventBus().consumer("service.country.getHousesInCountry", msg -> {
            getHousesInCountryAll(msg);
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

    private void getHousesInCountryAll(Message<Object> msg) {
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
