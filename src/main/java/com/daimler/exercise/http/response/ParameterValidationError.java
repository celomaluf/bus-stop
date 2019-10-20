package com.daimler.exercise.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  It's supports the ApiErrorResponse as a pojo to validate the parameters sent by user request
 */
@Data
@AllArgsConstructor
public class ParameterValidationError {

    private String dataPath;

    private String message;

    private String keyword;
}
