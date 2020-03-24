package com.kkh.app.util;

import com.kkh.app.exception.AuthExceptionMessage;

public class AuthUtil {

    public static String isRegexName(String userName) throws Exception {
        String nameRegex = "^[가-힣a-zA-Z0-9_]{1,20}$";
        if (!userName.matches(nameRegex))
            throw new Exception(AuthExceptionMessage.USER_NAME_REGEX_ERROR.getMessage());
        return null;
    }

    public static String isRegexPhoneNo(String phoneNo) throws Exception {
        String phoneNoRegex = "^[0-9]{10,11}$";
        if (!phoneNo.matches(phoneNoRegex))
            throw new Exception(AuthExceptionMessage.PHONE_NO_REGEX_ERROR.getMessage());
        return null;
    }

    public static String isRegexEmail(String email) throws Exception {
        String emailRegex = "^[a-zA-Z]{1}+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$";
        if (!email.matches(emailRegex))
            throw new Exception(AuthExceptionMessage.EMAIL_REGEX_ERROR.getMessage());
        return null;
    }
}
