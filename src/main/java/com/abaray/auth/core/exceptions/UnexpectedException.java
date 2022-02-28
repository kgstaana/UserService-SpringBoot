package com.abaray.auth.core.exceptions;

public class UnexpectedException extends GenericException {

    public UnexpectedException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
