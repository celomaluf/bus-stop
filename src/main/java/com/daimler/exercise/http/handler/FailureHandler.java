package com.daimler.exercise.http.handler;

import com.daimler.exercise.http.response.ApiErrorResponse;
import com.daimler.exercise.http.response.ParameterValidationError;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.validation.ValidationException;

import static com.daimler.exercise.http.util.ErrorHandlerUtil.getParameterNameFromValidationExceptionMessage;
import static com.daimler.exercise.http.util.ErrorHandlerUtil.removeParameterNameFromValidationExceptionMessage;

/**
 *  It handles eventual failures that may happen in between user requests and the data pass forward to the Event Bus
 */
public class FailureHandler implements Handler<RoutingContext> {

    private static final String APP_JSON = "application/json";
    private static final String CONTENT_TYPE = "content-type";
    private static final String VALIDATION_KEYWORD = "Invalid";

    public void handle(RoutingContext context) {

        Throwable failure = context.failure();

        if (failure instanceof ValidationException) {

            ValidationException throwable = (ValidationException) failure;

            ParameterValidationError validation = this.fromThrowable(throwable);

            handleOneValidationErrorExceptions(context, validation);

        } else if (failure instanceof ReplyException) {

            handleDefaultExceptions(context, HttpResponseStatus.BAD_REQUEST.code(), "Reply exception", failure.getMessage());

        }  else {
            handleDefaultExceptions(context, context.statusCode(), "Bad Request", failure.getMessage());
        }
    }

    private ParameterValidationError fromThrowable(ValidationException exception) {

        String parameterName = getParameterNameFromValidationExceptionMessage(exception.getMessage()).orElse(exception.parameterName());
        String keyword = exception.type() == null ? VALIDATION_KEYWORD : exception.type().name();
        String message = removeParameterNameFromValidationExceptionMessage(exception.getMessage()).orElse(exception.getMessage());

        return new ParameterValidationError(parameterName, message, keyword);
    }

    public static void handleDefaultExceptions(RoutingContext context, int statusCode, String message, String error) {

        ApiErrorResponse apiError = new ApiErrorResponse(statusCode, message, error);

        context.response()
                .setStatusCode(apiError.getStatus())
                .putHeader(CONTENT_TYPE, APP_JSON)
                .end(Json.encode(JsonObject.mapFrom(apiError)));
    }

    public static void handleOneValidationErrorExceptions(RoutingContext context, ParameterValidationError parameterValidationError) {

        context.response()
                .setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
                .putHeader(CONTENT_TYPE, APP_JSON)
                .end(Json.encode(JsonObject.mapFrom(new ApiErrorResponse(HttpResponseStatus.BAD_REQUEST.code(), parameterValidationError))));
    }

}
