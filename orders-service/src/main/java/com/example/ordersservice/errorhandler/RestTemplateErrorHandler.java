package com.example.ordersservice.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

    public class RestTemplateErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
            return (httpResponse.getStatusCode().isError());
        }

        @Override
        public void handleError(ClientHttpResponse httpResponse) throws IOException {
            HttpStatus statusCode = (HttpStatus) httpResponse.getStatusCode();
            if (statusCode.is5xxServerError()) {
                throw new HttpServerErrorException(statusCode, "A server error occured");
            } else if (statusCode.is4xxClientError()) {
                throw new HttpClientErrorException(statusCode, "A client error occured");
            } else {
                // Handle other types of errors if needed
                throw new RuntimeException("Unexpected HTTP error: " + statusCode);
            }
        }
    }

