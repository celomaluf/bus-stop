package com.daimler.exercise.http.service;

import com.daimler.exercise.http.service.impl.BusStopServiceImpl;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

/**
 *  RESTful API entry points
 *
 */
@WebApiServiceGen
public interface BusStopService {

    /** First question
     *
     * @param body
     * @param context
     * @param resultHandler
     */
    void findAllOperatorsByTS(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);


    /** Second question
     *
     * @param body
     * @param context
     * @param resultHandler
     */
    void findAllVehiclesByTSAndOperator(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);


    /** Third question
     *
     * @param body
     * @param context
     * @param resultHandler
     */
    void findAllVehiclesByTSAndOperatorAtABusStop(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);


    /** Fourth question
     *
     * @param body
     * @param context
     * @param resultHandler
     */
    void findAllTracesByTSAndVehicle(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

    static BusStopService create(Vertx vertx) {
        return new BusStopServiceImpl(vertx);
    }
}
