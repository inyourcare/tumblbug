package com.kkh.app.controller;

import com.kkh.app.jwt.JwtTokenUtil;
import com.kkh.app.request.JwtRequest;
import com.kkh.app.response.JwtResponse;
import com.kkh.app.security.CustomUserDetails;
import com.kkh.app.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getLoginId(), authenticationRequest.getPassword());
        final CustomUserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getLoginId());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
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