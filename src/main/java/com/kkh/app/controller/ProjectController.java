package com.kkh.app.controller;

import com.kkh.app.dto.ApiResponse;
import com.kkh.app.jpa.entity.ProjectEntity;
import com.kkh.app.request.project.*;
import com.kkh.app.response.project.ProjectGetDetailResponse;
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
    public ResponseEntity register(@CurrentUser CustomUserDetails user, @RequestBody ProjectRegisterRequest request) throws Exception {
        try {
            projectService.register(user, request);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).build());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@CurrentUser CustomUserDetails user, @RequestBody ProjectUpdateRequest request) throws Exception {
        try {
            projectService.update(user, request);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).build());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@CurrentUser CustomUserDetails user, @RequestBody ProjectDeleteRequest request) throws Exception {
        try {
            projectService.delete(user, request);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).build());
    }

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public ResponseEntity getList(@CurrentUser CustomUserDetails user, @RequestBody ProjectGetListRequest request) throws Exception {
        List<ProjectEntity> resultList = null;
        try {
            resultList = projectService.getList(user, request);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).result(ProjectGetListResponse.builder().projectEntityList(resultList).build()).build());
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public ResponseEntity getDetail(@CurrentUser CustomUserDetails user, @RequestBody ProjectGetDetailRequest request) throws Exception {
        ProjectEntity projectEntity = null;
        try {
            projectEntity = projectService.getDetail(user, request);
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
        return ResponseEntity.ok().body(ApiResponse.builder().success(true).result(ProjectGetDetailResponse.builder().projectEntity(projectEntity).build()).build());
    }
}
