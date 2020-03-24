package com.kkh.app.exception;

import lombok.Getter;

@Getter
public enum UserExceptionMessage {
    USER_NOT_FOUND("User not found"),
    ;
    final private String message;

    UserExceptionMessage(String message) {
        this.message = message;
    }
}
