package com.daimler.exercise.db.handler;

import com.daimler.exercise.db.dao.BusStopDataSource;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.Set;

/** It handles the Vehicle Trace requests that come from the Http Verticle through the event bus
 *
 * @param <T>
 */
@Log4j
public class VehicleTracesHandler<T> implements Handler<Message<T>> {

    private final BusStopDataSource busStopDS;

    public VehicleTracesHandler(final BusStopDataSource busStopDS) {
        this.busStopDS = busStopDS;
    }

    @Override
    public void handle(Message<T> message) {

        JsonObject bodyAsJson = (JsonObject) message.body();
        try {
            Set<String> gpsEntries = this.busStopDS.
                    findAllTracesByVehicleOrderedByTimestamp(
                            bodyAsJson.getLong("startTime"),
                            bodyAsJson.getLong("endTime"),
                            bodyAsJson.getInteger("vehicleId"));

            message.reply(new JsonObject().put("gpsEntries", new ArrayList<>(gpsEntries)));

        } catch (Exception e) {
            String errorMessage = "Error while processing find all vehicles traces: \n";
            log.error(errorMessage.concat(e.getMessage()), e);
            message.fail( 520, errorMessage);
        }
    }
}
