package com.kkh.app.security;

import lombok.Getter;

@Getter
public enum SecurityCode {
    NORMAL("Normal"),
    ;

    final private String code;

    private SecurityCode(String code) {
        this.code = code;
    }
}
