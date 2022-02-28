package com.kenjie.auth.core.exceptions;

import com.kenjie.auth.core.enums.UserErrorCode;

public class UnexpectedException extends GenericException {

    public UnexpectedException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
