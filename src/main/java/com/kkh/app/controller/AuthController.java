package com.kkh.app.controller;

import com.kkh.app.request.SignUpRequest;
import com.kkh.app.service.AuthService;
import com.kkh.app.util.AuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Api(value = "auth api", consumes = "application/json")
@RequestMapping("/v0/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "signUp", response = Map.class)
    @RequestMapping(path = "/signUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        validate(signUpRequest);
        authService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }

    private void validate(SignUpRequest signUpRequest) throws Exception {
        AuthUtil.isRegexName(signUpRequest.getName());
        AuthUtil.isRegexEmail(signUpRequest.getEmail());
        AuthUtil.isRegexPhoneNo(signUpRequest.getPhoneNo());
    }
}
