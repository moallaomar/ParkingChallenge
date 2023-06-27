package com.example.parkingchallenge.exceptions;

public class RestClientException extends RuntimeException {
    public RestClientException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public RestClientException(String errorMessage) {
        super(errorMessage);
    }
}
