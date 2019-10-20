package com.daimler.exercise.db.handler;

import com.daimler.exercise.db.dao.BusStopDataSource;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import lombok.extern.log4j.Log4j;

import java.util.List;

/** It handles the Vehicle requests that come from the Http Verticle through the event bus
 *
 * @param <T>
 */
@Log4j
public class VehicleHandler<T> implements Handler<Message<T>> {

    private final BusStopDataSource busStopDS;

    public VehicleHandler(final BusStopDataSource busStopDS) {
        this.busStopDS = busStopDS;
    }

    @Override
    public void handle(Message<T> message) {

        JsonObject bodyAsJson = (JsonObject) message.body();
        List<Integer> listVehiclesId;
        try {
            listVehiclesId =
                this.busStopDS.
                    findAllVehiclesIdByTimestampAndOperatorAndAtStop(
                                        bodyAsJson.getLong("startTime"),
                                        bodyAsJson.getLong("endTime"),
                                        bodyAsJson.getString("operator"),
                                        bodyAsJson.getBoolean("atBusStop") );

            message.reply(new JsonObject().put("vehiclesIds", listVehiclesId));

        } catch (Exception e) {
            String errorMessage = "Error while processing find all vehicles: \n";
            log.error(errorMessage.concat(e.getMessage()), e);
            message.fail( 520, errorMessage);
        }
    }
}
