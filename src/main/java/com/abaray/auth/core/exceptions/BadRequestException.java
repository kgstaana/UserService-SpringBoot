package com.abaray.auth.core.exceptions;

public class BadRequestException extends GenericException {

    public BadRequestException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
