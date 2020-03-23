package com.kkh.app.service;

import com.kkh.app.jpa.entity.UserEntity;
import com.kkh.app.jpa.repo.UserRepository;
import com.kkh.app.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public void signUp(SignUpRequest signUpRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userId = String.valueOf(Thread.currentThread().getId()) + new Date().getTime();
        userRepository.save(UserEntity.builder()
                .userId(Long.parseLong(userId))
                .loginId(signUpRequest.getLoginId())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build());
    }
}
