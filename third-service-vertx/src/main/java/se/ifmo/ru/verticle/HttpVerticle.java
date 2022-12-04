package se.ifmo.ru.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.Message;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.RoutingContext;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.model.bus.SponsorBusData;
import se.ifmo.ru.model.web.DonationRequestDto;
import se.ifmo.ru.utils.HttpUtils;
import se.ifmo.ru.utils.XmlUtils;

@Slf4j
public class HttpVerticle extends AbstractVerticle {

    @Override
    public Uni<Void> asyncStart() {
        Uni<String> deployCountriesVerticelUni = vertx.deployVerticle(new CountryVerticle());
        Uni<String> deploySponsorVerticelUni = vertx.deployVerticle(new SponsorVerticle());

        Router router = routes();

        Uni<HttpServer> httpServerUni = vertx.createHttpServer()
                .requestHandler(router::handle)
                .listen(8080)
                .onItem().invoke(() -> log.info("HTTP server listening on port 8888"));

        return Uni.combine().all().unis(deployCountriesVerticelUni, deploySponsorVerticelUni, httpServerUni).discardItems();
    }

    private Router routes() {
        Router router = Router.router(vertx);

        router.get("/secret/countries").respond(this::getAllCountries);
        router.get("/secret/countries/:id/houses").respond(this::getHousesInCountry);
        router.post("/secret/donate/:houseId/:sponsorId").handler(BodyHandler.create()).respond(this::sponsorHouse);

        return router;
    }

    private Uni<String> getAllCountries(RoutingContext ctx) {
        return HttpUtils.convertEventBusMessageToResponse(
                vertx.eventBus().request("service.country.getAll", "")
        );
    }

    private Uni<String> getHousesInCountry(RoutingContext ctx) {
        String countryId = ctx.pathParam("id");

        return HttpUtils.convertEventBusMessageToResponse(
                vertx.eventBus().request("service.country.getHousesInCountry", countryId)
        );
    }

    private Uni<Void> sponsorHouse(RoutingContext ctx) {
        String houseId = ctx.pathParam("houseId");
        String sponsorId = ctx.pathParam("sponsorId");
        DonationRequestDto requestDto = XmlUtils
                .stringToObject(ctx.body().asString(), DonationRequestDto.class);

        SponsorBusData sponsorBusData = new SponsorBusData(sponsorId, houseId, requestDto.getAmount());

        ctx.request().response().setStatusCode(204).end();

        return vertx.eventBus().request("service.sponsor.sponsorHouse", XmlUtils.objectToXmlString(sponsorBusData))
                .replaceWithVoid();
    }
}
