package com.daimler.exercise.http.response;

import com.daimler.exercise.http.util.ErrorHandlerUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.JsonObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  It's the response data structure for API errors
 */
@Getter
public class ApiErrorResponse {

    private int status;
    private String message;
    private List<String> errors;
    private List<ParameterValidationError> validations;

    private static final String VALIDATION_ERROR_MESSAGE = "Parameter validation error";

    public ApiErrorResponse(int status, String message, List<String> errors) {
        this.status = ErrorHandlerUtil.getValidHttpStatusCode(status);;
        this.message = message;
        this.errors = errors;
        this.validations = new ArrayList<>();
    }

    public ApiErrorResponse(int status, ParameterValidationError validationError) {
        this.status = ErrorHandlerUtil.getValidHttpStatusCode(status);
        this.message = VALIDATION_ERROR_MESSAGE;
        this.errors = new ArrayList<>();
        this.validations = Collections.singletonList(validationError);
    }

    public ApiErrorResponse(int status, List<ParameterValidationError> validationErrors) {
        this.status = ErrorHandlerUtil.getValidHttpStatusCode(status);;
        this.message = VALIDATION_ERROR_MESSAGE;
        this.errors = new ArrayList<>();
        this.validations = validationErrors;
    }

    public ApiErrorResponse(int status, String message, String error) {
        this.status = ErrorHandlerUtil.getValidHttpStatusCode(status);;
        this.message = message;
        this.errors = Collections.singletonList(error);
        this.validations = new ArrayList<>();
    }

    public static ApiErrorResponse fromJsonObjectError(JsonObject errorJson, int statusCode) {

        String error = HttpResponseStatus.valueOf(statusCode).reasonPhrase();
        String errorMessage = "";

        if (errorJson.containsKey("error_description")) {
            errorMessage = errorJson.getString("error_description");
        } else if (errorJson.containsKey("message")) {
            errorMessage = errorJson.getString("message");
        }

        return new ApiErrorResponse(statusCode, error, errorMessage);
    }

    public void clearErrorsAndAppend(String error) {
        this.errors = Collections.singletonList(error);
    }
}
