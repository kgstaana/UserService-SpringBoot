package com.kenjie.auth.core.exceptions;

import com.kenjie.auth.core.enums.UserErrorCode;

public class DuplicateRecordException extends GenericException {

    public DuplicateRecordException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
