package com.example.ordersservice.errorhandler;

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
            if (httpResponse.getStatusCode().is5xxServerError()) {
                throw new HttpServerErrorException(httpResponse.getStatusCode());
            } else if (httpResponse.getStatusCode().is4xxClientError()) {
                throw new HttpClientErrorException(httpResponse.getStatusCode());
            } else {

                throw new RuntimeException("Unexpected HTTP error: " + httpResponse.getStatusCode());
            }
        }
    }

