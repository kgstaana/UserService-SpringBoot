package com.abaray.auth.core.exceptions;

public class GenericException extends Exception {

    private String errorCode;

    public GenericException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
