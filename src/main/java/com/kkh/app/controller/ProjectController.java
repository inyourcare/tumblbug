package com.kkh.app.controller;

import com.kkh.app.jpa.entity.ProjectEntity;
import com.kkh.app.request.project.ProjectDeleteRequest;
import com.kkh.app.request.project.ProjectGetListRequest;
import com.kkh.app.request.project.ProjectRegisterRequest;
import com.kkh.app.request.project.ProjectUpdateRequest;
import com.kkh.app.response.project.ProjectGetListResponse;
import com.kkh.app.security.CurrentUser;
import com.kkh.app.security.CustomUserDetails;
import com.kkh.app.service.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "project api", consumes = "application/json")
@RequestMapping("/v0/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@CurrentUser CustomUserDetails user , @RequestBody ProjectRegisterRequest request) throws Exception {
        projectService.register(user , request);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@CurrentUser CustomUserDetails user , @RequestBody ProjectUpdateRequest request) throws Exception {
        projectService.update(user , request);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@CurrentUser CustomUserDetails user , @RequestBody ProjectDeleteRequest request) throws Exception {
        projectService.delete(user , request);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public ResponseEntity getList(@CurrentUser CustomUserDetails user , @RequestBody ProjectGetListRequest request) throws Exception {
        List<ProjectEntity> resultList = projectService.getList(user , request);
        return ResponseEntity.ok().body(ProjectGetListResponse.builder().projectEntityList(resultList).build());
    }
}
