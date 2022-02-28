package com.kenjie.auth.core.exceptions;

import com.kenjie.auth.core.enums.UserErrorCode;

public class NotFoundException extends GenericException {

    public NotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
