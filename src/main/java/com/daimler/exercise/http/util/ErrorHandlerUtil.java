package com.daimler.exercise.http.util;

import com.daimler.exercise.http.response.ApiErrorResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;

import java.util.Optional;
import java.util.regex.Matcher;

public class ErrorHandlerUtil {

    private static final int DEFAULT_HTTP_STATUS_CODE = 400;

    private ErrorHandlerUtil() {}

    public static int getStatusCodeFromFailedMessageFuture(AsyncResult<Void> result) {
        return getValidHttpStatusCode(getStatusCode(result.cause()));
    }

    public static int getStatusCodeFromFailedMessageFutureObject(AsyncResult<Message<Object>> result) {
        return getValidHttpStatusCode(getStatusCode(result.cause()));
    }

    public static ApiErrorResponse fromThrowableToApiError(Throwable cause) {

        if (cause == null) {
            return ApiErrorResponse.fromJsonObjectError(new JsonObject(), 200);
        }

        JsonObject errorAsJson = ErrorHandlerUtil.fromStringToJsonObjectError(cause.getMessage());
        int statusCode = getValidHttpStatusCode(getStatusCode(cause));

        return ApiErrorResponse.fromJsonObjectError(errorAsJson, statusCode);
    }

    public static int getStatusCode(Throwable cause) {
        int statusCode = DEFAULT_HTTP_STATUS_CODE;
        if (cause instanceof ReplyException) {
            ReplyException replyException = (ReplyException) cause;
            statusCode = replyException.failureCode();
        }

        return statusCode;
    }

    public static int getValidHttpStatusCode(int status) {
        if (status < 100 || status >= 600) {
            return DEFAULT_HTTP_STATUS_CODE;
        }

        return status;
    }

    public static JsonObject fromStringToJsonObjectError(String errorMessage) {
        try {
            return new JsonObject(errorMessage);
        } catch (DecodeException e) {}

        ApiErrorResponse apiError = new ApiErrorResponse(DEFAULT_HTTP_STATUS_CODE, errorMessage, errorMessage);

        return JsonObject.mapFrom(apiError);
    }

    public static Optional<String> getParameterNameFromValidationExceptionMessage(String exceptionMessage) {

        if (exceptionMessage == null) {
            return Optional.empty();
        }

        Optional<String> parameterName = Optional.empty();

        Matcher matcher = RegexUtil.VALIDATION_ERROR_PATTERN.matcher(exceptionMessage);

        if (matcher.lookingAt()) {
            String pattern = exceptionMessage.substring(matcher.start(), matcher.end());
            parameterName = Optional.of(pattern.replaceFirst(": ", ""));
        }

        return parameterName;
    }

    public static Optional<String> removeParameterNameFromValidationExceptionMessage(String exceptionMessage) {

        if (exceptionMessage == null) {
            return Optional.empty();
        }

        Optional<String> parameterName = Optional.empty();;

        Matcher matcher = RegexUtil.VALIDATION_ERROR_PATTERN.matcher(exceptionMessage);

        if (matcher.lookingAt()) {
            parameterName = Optional.of(exceptionMessage.replaceFirst(RegexUtil.VALIDATION_ERROR_PATTERN.pattern(), ""));
        }

        return parameterName;
    }
}
