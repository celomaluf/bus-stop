package com.daimler.exercise.http;

import com.daimler.exercise.http.handler.FailureHandler;
import com.daimler.exercise.http.service.BusStopService;
import io.vertx.core.*;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.serviceproxy.ServiceBinder;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

import static com.daimler.exercise.http.util.ServiceNameEnum.READ_BUS_STOP;

/**
 *  The entry point of the Http Verticle
 */
@Log4j
public class HttpVerticle extends AbstractVerticle {

    private HttpServer server;

    private List<MessageConsumer<JsonObject>> serviceConsumers;

    @Override
    public void start(Promise<Void> promise) {
        System.setProperty("user.timezone", "GMT");
        startResourceServices();
        startHttpServer().setHandler(promise);
    }

    private void startResourceServices() {
        ServiceBinder serviceBinder = new ServiceBinder(vertx);
        serviceConsumers = new ArrayList<>();

        BusStopService busStopService = BusStopService.create(vertx);

        serviceConsumers.add(serviceBinder
                .setAddress(READ_BUS_STOP.getName())
                .register(BusStopService.class, busStopService));

    }

    private Future<Void> startHttpServer() {

        Promise<Void> promise = Promise.promise();
            OpenAPI3RouterFactory.create(this.vertx, "/openapi.yaml", openAPI3RouterFactoryAsyncResult -> {
            if (openAPI3RouterFactoryAsyncResult.succeeded()) {
                OpenAPI3RouterFactory routerFactory = openAPI3RouterFactoryAsyncResult.result();

                // Mount services on event bus based on extensions
                routerFactory.mountServicesFromExtensions();
                createHttpServer(promise, openAPI3RouterFactoryAsyncResult, routerFactory);

            } else {
                // Something went wrong during router factory initialization
                log.error("Could not start OpenAPI3RouterFactory", openAPI3RouterFactoryAsyncResult.cause());
                promise.fail(openAPI3RouterFactoryAsyncResult.cause());
            }
        });
        return promise.future();
    }

    private void createHttpServer(Promise<Void> promise, AsyncResult<OpenAPI3RouterFactory> openAPI3RouterFactoryAsyncResult, OpenAPI3RouterFactory routerFactory) {

        Router router = routerFactory.getRouter();
        router.route().failureHandler(new FailureHandler());

        final Integer httpPort = 8080;
        this.server = vertx.createHttpServer(new HttpServerOptions().
                setCompressionSupported(true)).
                requestHandler(router).listen(httpPort, asyncResult -> {

            if (asyncResult.succeeded()) {
                log.info("[HTTP server running on port]: " + httpPort);
                promise.complete();
            }
            else {
                log.error("Could not start a HTTP server", asyncResult.cause());
                promise.fail(openAPI3RouterFactoryAsyncResult.cause());
            }
        });
    }

    @Override
    public void stop() {
        log.info("[HTTP verticled stopped]");
        this.server.close();
        for (MessageConsumer<JsonObject> consumer : serviceConsumers) {
            consumer.unregister();
        }
    }

    public static void main(String[] args ) {

        VertxOptions vOptions = new VertxOptions();
        log.info("Vert.x default event loop size: " + vOptions.DEFAULT_EVENT_LOOP_POOL_SIZE);
        Vertx.clusteredVertx(vOptions, results -> {
            if (results.succeeded()) {
                Vertx vertx = results.result();
                vertx.deployVerticle(HttpVerticle.class.getName());
            }
        });
    }

}
