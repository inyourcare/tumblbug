package com.kkh.app.exception;

import lombok.Getter;

@Getter
public enum  AuthExceptionCode {

    PHONE_NO_REGEX_ERROR("Phone number regex error"),
    EMAIL_REGEX_ERROR("email regex error"),
    USER_NAME_REGEX_ERROR("name regex error"),
            ;
    final private String message;

    AuthExceptionCode(String message) {
        this.message = message;
    }
}
