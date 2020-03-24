package com.kkh.app.controller;

import com.kkh.app.dto.ApiResponse;
import com.kkh.app.jwt.JwtTokenUtil;
import com.kkh.app.request.JwtRequest;
import com.kkh.app.request.SignUpRequest;
import com.kkh.app.response.JwtResponse;
import com.kkh.app.security.CustomUserDetails;
import com.kkh.app.security.JwtUserDetailsService;
import com.kkh.app.service.AuthService;
import com.kkh.app.util.AuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @ApiOperation(value = "signUp", response = Map.class)
    @RequestMapping(path = "/signUp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        validate(signUpRequest);
        try {
            authService.signUp(signUpRequest);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).build());
    }

    private void validate(SignUpRequest signUpRequest) throws Exception {
        AuthUtil.isRegexName(signUpRequest.getName());
        AuthUtil.isRegexEmail(signUpRequest.getEmail());
        AuthUtil.isRegexPhoneNo(signUpRequest.getPhoneNo());
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final String token;
        try {
            authenticate(authenticationRequest.getLoginId(), authenticationRequest.getPassword());
            final CustomUserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getLoginId());
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).result(new JwtResponse(token)).build());
    }

    private void authenticate(String loginId, String password) throws Exception {
        try {
            // get user info from loadUserByUsername in userDetailsService
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
