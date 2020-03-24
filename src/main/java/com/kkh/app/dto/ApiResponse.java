package com.kkh.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object result;

    @Builder
    public ApiResponse(Boolean success, String message, Object result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }
}
