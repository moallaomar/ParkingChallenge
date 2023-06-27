package com.example.parkingchallenge.exceptions;

public class InvalidGeoCodeException extends RuntimeException {
    public InvalidGeoCodeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public InvalidGeoCodeException(String errorMessage) {
        super(errorMessage);
    }
}
