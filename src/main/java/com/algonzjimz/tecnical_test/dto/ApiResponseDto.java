package com.algonzjimz.tecnical_test.dto;

import org.springframework.http.HttpStatus;

public class ApiResponseDto<T> {
    private int status;
    private String message;
    private T data;

    public ApiResponseDto(HttpStatus status, String message, T data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResponseDto<T> success(String message, T data) {
        return new ApiResponseDto<>(HttpStatus.OK, message, data);
    }

    public static <T> ApiResponseDto<T> created(String message, T data) {
        return new ApiResponseDto<>(HttpStatus.CREATED, message, data);
    }

    public static <T> ApiResponseDto<T> error(HttpStatus status, String message) {
        return new ApiResponseDto<>(status, message, null);
    }
}
