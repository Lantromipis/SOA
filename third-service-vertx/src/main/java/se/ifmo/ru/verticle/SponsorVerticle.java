package se.ifmo.ru.verticle;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.model.bus.SponsorBusData;
import se.ifmo.ru.model.storage.HouseEntity;
import se.ifmo.ru.model.storage.SponsorEntity;
import se.ifmo.ru.utils.XmlUtils;

@Slf4j
public class SponsorVerticle extends AbstractVerticle {
    @Override
    public Uni<Void> asyncStart() {
        vertx.eventBus().consumer("service.sponsor.sponsorHouse", msg -> {
            sponsor(msg);
        });

        return Uni.createFrom().voidItem();
    }

    private void sponsor(Message<Object> msg) {
        SponsorBusData sponsorBusData = XmlUtils.stringToObject((String) msg.body(), SponsorBusData.class);

        DatabaseVerticle.getSessionFactory()
                .withTransaction((session, transaction) -> session
                        .createQuery("from SponsorEntity where id = ?1", SponsorEntity.class)
                        .setParameter(1, Long.parseLong(sponsorBusData.getSponsorId()))
                        .getSingleResult()
                )
                .subscribe()
                .with(ok -> {
                            DatabaseVerticle.getSessionFactory().withTransaction(
                                            (session, transaction) -> session
                                                    .createQuery("from HouseEntity where id = ?1", HouseEntity.class)
                                                    .setParameter(1, Long.parseLong(sponsorBusData.getHouseId()))
                                                    .getSingleResult()
                                                    .onItem()
                                                    .invoke(houseEntity -> {
                                                        houseEntity.setSponsor(ok);
                                                        houseEntity.setDonated(true);
                                                        session.persist(houseEntity);
                                                        session.flush();
                                                        log.info("************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");

                                                    })
                                    )
                                    .subscribe()
                                    .with(ok2 -> {
                                                log.info("success");
                                                msg.reply("");
                                            },
                                            failure -> {
                                                log.error(failure.getMessage(), failure);
                                                msg.reply("");
                                            }
                                    );
                        },
                        failure -> {
                            log.error(failure.getMessage(), failure);
                            msg.reply("");
                        }
                );
    }
}
