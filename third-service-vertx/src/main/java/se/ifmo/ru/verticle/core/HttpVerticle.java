package se.ifmo.ru.verticle.core;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.Message;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import se.ifmo.ru.verticle.service.CountryVerticle;

@Slf4j
public class HttpVerticle extends AbstractVerticle {

    @Override
    public Uni<Void> asyncStart() {
        Uni<String> deployCountriesServiceVerticelUni = vertx.deployVerticle(new CountryVerticle());

        Router router = routes();

        Uni<HttpServer> httpServerUni = vertx.createHttpServer()
                .requestHandler(router::handle)
                .listen(8888)
                .onItem().invoke(() -> log.info("HTTP server listening on port 8888"));

        return Uni.combine().all().unis(deployCountriesServiceVerticelUni, httpServerUni).discardItems();
    }

    private Router routes() {
        Router router = Router.router(vertx);

        router.get("/secret/countries").respond(this::getAllCountries);
        router.get("/secret/countries/:id/houses").respond(this::getHousesInCountry);
        router.get("/hello").respond(ctx -> Uni.createFrom().item("Hello world"));

        return router;
    }

    private Uni<String> getAllCountries(RoutingContext ctx) {
        return vertx.eventBus().request("service.country.getAll", "")
                .onItem()
                .transform(Message::body)
                .onItem()
                .castTo(String.class);
    }

    private Uni<String> getHousesInCountry(RoutingContext ctx) {
        String countryId = ctx.pathParam("id");

        return vertx.eventBus().request("service.country.getHousesInCountry", countryId)
                .onItem()
                .transform(Message::body)
                .onItem()
                .castTo(String.class);
    }
}
