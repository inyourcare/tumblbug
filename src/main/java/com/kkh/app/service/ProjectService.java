package com.kkh.app.service;

import com.kkh.app.request.project.ProjectDeleteRequest;
import com.kkh.app.request.project.ProjectRegisterRequest;
import com.kkh.app.request.project.ProjectUpdateRequest;
import com.kkh.app.security.CustomUserDetails;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    public void register(CustomUserDetails user, ProjectRegisterRequest request) {
        System.out.println("register");
    }

    public void update(CustomUserDetails user, ProjectUpdateRequest request) {
    }

    public void delete(CustomUserDetails user, ProjectDeleteRequest request) {
    }
}
