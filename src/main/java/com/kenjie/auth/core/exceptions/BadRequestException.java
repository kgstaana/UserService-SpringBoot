package com.kenjie.auth.core.exceptions;

import com.kenjie.auth.core.enums.UserErrorCode;

public class BadRequestException extends GenericException {

    public BadRequestException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
