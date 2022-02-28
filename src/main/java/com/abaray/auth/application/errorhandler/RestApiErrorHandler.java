package com.abaray.auth.application.errorhandler;

import com.abaray.auth.application.DTOs.ErrorResponseDTO;
import com.abaray.auth.application.enums.GenericErrorCode;
import com.abaray.auth.application.errorhandler.exceptions.GenericBadRequestException;
import com.abaray.auth.application.errorhandler.exceptions.UnauthenticatedException;
import com.abaray.auth.application.errorhandler.exceptions.UnauthorizedException;
import com.abaray.auth.core.exceptions.DuplicateRecordException;
import com.abaray.auth.application.DTOs.ErrorDTO;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.exceptions.NotFoundException;
import com.abaray.auth.core.exceptions.UnexpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiErrorHandler {

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthenticated() {
        ErrorDTO errorData = new ErrorDTO(GenericErrorCode.AUTH_000401.name(), "Unauthenticated");
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnauthorized() {
        ErrorDTO errorData = new ErrorDTO(GenericErrorCode.AUTH_000403.name(), "Unauthorized");
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GenericBadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericBadRequest() {
        ErrorDTO errorData = new ErrorDTO(GenericErrorCode.REQ_000400.name(), "Bad Request");
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericError() {
        ErrorDTO errorData = new ErrorDTO(GenericErrorCode.REQ_000500.name(),"Unexpected error");
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(BadRequestException e) {
        ErrorDTO errorData = new ErrorDTO(e.getErrorCode(), e.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(NotFoundException e) {
        ErrorDTO errorData = new ErrorDTO(e.getErrorCode(), e.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateRecord(DuplicateRecordException e) {
        ErrorDTO errorData = new ErrorDTO(e.getErrorCode(), e.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnexpected(UnexpectedException e) {
        ErrorDTO errorData = new ErrorDTO(e.getErrorCode(), e.getMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(errorData);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
