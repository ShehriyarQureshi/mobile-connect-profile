package com.ufone.api.errors;

import com.ufone.api.errors.BaseErrorResponse;
import com.ufone.api.request.Request;

import javax.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

public class InvalidResponseType extends BaseErrorResponse {
        private final String error = "invalid_request";
        private final String errorDescription =
            "MANDATORY parameter response_type is missing or value is invalid";
        private String baseResponse;

        @Override
        public String getErrorTitle() {
                return this.error;
        }

        @Override
        public String getErrorDescription() {
                return this.errorDescription;
        }

        public Response buildAndReturnResponse(Request request)
            throws UnsupportedEncodingException {
                InvalidResponseType errorResponse = new InvalidResponseType();
                baseResponse = errorResponse.buildBaseErrorResponse(request.getResponseType());
                baseResponse = errorResponse.addStateQueryParam(baseResponse, request.getState());
                baseResponse = errorResponse.addCorrelationIDQueryParam(
                    baseResponse, request.getCorrelationID());
                return errorResponse.returnResponse(baseResponse);
        }
}
