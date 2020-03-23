package com.kkh.app.request;

import lombok.Data;

@Data
public class JwtRequest {
    private static final long serialVersionUID = 5926468583005150707L;

    private String loginId;
    private String password;
}
