package com.abaray.auth.application.DTOs;

public class ErrorResponseDTO {

    private ErrorDTO error;

    public ErrorResponseDTO(ErrorDTO error) {
        this.error = error;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
