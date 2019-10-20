package com.daimler.exercise.db.handler;

import com.daimler.exercise.db.dao.BusStopDataSource;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j;

import java.util.List;

/** It handles the Operator requests that come from the Http Verticle through the event bus
 *
 * @param <T>
 */
@Log4j
public class OperatorHandler<T> implements Handler<Message<T>> {

    private final BusStopDataSource busStopDS;

    public OperatorHandler(final BusStopDataSource busStopDS) {
        this.busStopDS = busStopDS;
    }

    @Override
    public void handle(Message<T> message) {

        JsonObject bodyAsJson = (JsonObject) message.body();
        try {
            List<String> listOfOperators =
                    this.busStopDS.
                            findAllOperatorsByTimestamp(
                                    bodyAsJson.getLong("startTime"), bodyAsJson.getLong("endTime"));
            message.reply(new JsonObject().put("operators", listOfOperators));

        } catch (Exception e) {
            String errorMessage = "Error while processing find all operators: \n";
            log.error(errorMessage.concat(e.getMessage()), e);
            message.fail( 520, errorMessage);
        }
    }
}
