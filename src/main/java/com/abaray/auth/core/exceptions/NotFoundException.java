package com.abaray.auth.core.exceptions;

public class NotFoundException extends GenericException {

    public NotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
