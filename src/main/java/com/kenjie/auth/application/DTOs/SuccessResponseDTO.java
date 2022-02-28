package com.kenjie.auth.application.DTOs;

public class SuccessResponseDTO<T> {

    private T data;

    public SuccessResponseDTO(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
