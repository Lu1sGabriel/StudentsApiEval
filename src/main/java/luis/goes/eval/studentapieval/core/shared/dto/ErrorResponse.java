package luis.goes.eval.studentapieval.core.shared.dto;

import luis.goes.eval.studentapieval.core.shared.utils.StatusCode;

public record ErrorResponse(boolean success, String message, int statusCode) {

    public ErrorResponse(String message, StatusCode statusCode) {
        this(false, message, statusCode.getCode());
    }

    public ErrorResponse(String message, int statusCode) {
        this(false, message, statusCode);
    }

}