package com.kenjie.auth.core.exceptions;

import com.kenjie.auth.core.enums.UserErrorCode;

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
