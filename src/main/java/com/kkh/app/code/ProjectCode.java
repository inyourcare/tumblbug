package com.kkh.app.code;

import lombok.Getter;

@Getter
public enum ProjectCode {
    //준비중 , 진행중 , 성공 , 실패
    STATE_READY("R", "준비중"),
    STATE_STANDING("S", "진행중"),
    STATE_DONE("D", "성공"),
    STATE_FAIL("F", "실패"),
    ;
    private String code;
    private String message;

    private ProjectCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
