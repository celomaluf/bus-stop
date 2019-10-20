package com.daimler.exercise.http.service.impl;

import com.daimler.exercise.http.response.ApiErrorResponse;
import com.daimler.exercise.http.service.BusStopService;
import com.daimler.exercise.http.util.ErrorHandlerUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

import static com.daimler.exercise.http.util.EventBusEnum.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;


public class BusStopServiceImpl implements BusStopService {

    private Vertx vertx;

    public BusStopServiceImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void findAllOperatorsByTS(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        sendToEventBus(resultHandler, body, OPERATORS_BY_TS.getName());
    }

    @Override
    public void findAllVehiclesByTSAndOperator(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        sendToEventBus(resultHandler, body, VEHICLES_BY_TS_OPERATOR.getName());
    }

    @Override
    public void findAllVehiclesByTSAndOperatorAtABusStop(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        sendToEventBus(resultHandler, body, VEHICLES_BY_TS_OPERATOR_AT_STOP.getName());
    }

    @Override
    public void findAllTracesByTSAndVehicle(JsonObject body, OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        sendToEventBus(resultHandler, body, TRACES_BY_TS_VEHICLE.getName());
    }

    private void sendToEventBus(Handler<AsyncResult<OperationResponse>> resultHandler, JsonObject request, String eventBusName) {

        try {

            vertx.eventBus().request(eventBusName, request, reply -> {
                if (reply.succeeded()) {

                    JsonObject replyResults = new JsonObject(reply.result().body().toString());
                    OperationResponse operationResponse = OperationResponse.completedWithJson(replyResults);
                    operationResponse.setStatusCode(OK.code());
                    resultHandler.handle(
                            Future.succeededFuture(operationResponse)
                    );

                } else {
                    ApiErrorResponse error = ErrorHandlerUtil.fromThrowableToApiError(reply.cause());
                    OperationResponse operationResponse = OperationResponse.completedWithJson(JsonObject.mapFrom(error));
                    operationResponse.setStatusCode(error.getStatus());
                    resultHandler.handle(
                            Future.succeededFuture(operationResponse)
                    );
                }
            });
        } catch (Throwable e ) {
            e.printStackTrace();
        }
    }
}
