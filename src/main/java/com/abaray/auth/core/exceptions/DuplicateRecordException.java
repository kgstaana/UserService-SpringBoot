package com.abaray.auth.core.exceptions;

public class DuplicateRecordException extends GenericException {

    public DuplicateRecordException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
