package com.kkh.app.exception;

import lombok.Getter;

@Getter
public enum ProjectExceptionMessage {
    PROJECT_NOT_FOUND("Project not found"),
            ;
    final private String message;

    ProjectExceptionMessage(String message) {
        this.message = message;
    }
}
