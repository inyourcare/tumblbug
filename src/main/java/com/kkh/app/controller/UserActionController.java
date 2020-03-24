package com.kkh.app.controller;

import com.kkh.app.request.project.ProjectRegisterRequest;
import com.kkh.app.request.useraction.UserActionRequest;
import com.kkh.app.security.CurrentUser;
import com.kkh.app.security.CustomUserDetails;
import com.kkh.app.service.UserActionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "user action api", consumes = "application/json")
@RequestMapping("/v0/userAction")
public class UserActionController {
    @Autowired
    UserActionService userActionService;

    @RequestMapping(value = "/supportProject", method = RequestMethod.POST)
    public ResponseEntity supportProject(@CurrentUser CustomUserDetails user , @RequestBody UserActionRequest request) throws Exception {
        userActionService.supportProject(user , request);
        return ResponseEntity.ok().build();
    }
}
