package com.kkh.app.service;

import com.kkh.app.code.ProjectCode;
import com.kkh.app.exception.ProjectExceptionMessage;
import com.kkh.app.exception.UserExceptionMessage;
import com.kkh.app.jpa.entity.ProjectEntity;
import com.kkh.app.jpa.entity.UserEntity;
import com.kkh.app.jpa.repo.ProjectRepository;
import com.kkh.app.jpa.repo.UserRepository;
import com.kkh.app.request.project.ProjectDeleteRequest;
import com.kkh.app.request.project.ProjectRegisterRequest;
import com.kkh.app.request.project.ProjectUpdateRequest;
import com.kkh.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;


    public void register(CustomUserDetails user, ProjectRegisterRequest request) throws Exception {
        UserEntity userEntity = userRepository.findById(Long.parseLong(user.getUserId())).orElseThrow(()->new Exception(UserExceptionMessage.USER_NOT_FOUND.getMessage()));
        Date now = new Date();
        projectRepository.save(ProjectEntity.builder()
                .creator(userEntity)
                .modifier(userEntity)
                .donationAccumulated(0)
                .donationCount(0)
                .donationGoal(request.getDonationGoal())
                .projectName(request.getProjectName())
                .projectDescription(request.getProjectDescription())
                .projectStartTimestamp(request.getProjectStartTime())
                .projectEndTimestamp(request.getProjectEndTime())
                .registeredTimestamp(now)
                .updatedTimestamp(now)
                .state(ProjectCode.STATE_READY.getCode())
                .build());
    }

    public void update(CustomUserDetails user, ProjectUpdateRequest request) throws Exception {
        UserEntity userEntity = userRepository.findById(Long.parseLong(user.getUserId())).orElseThrow(()->new Exception(UserExceptionMessage.USER_NOT_FOUND.getMessage()));
        Date now = new Date();
        ProjectEntity projectEntity = projectRepository.findById(request.getProjectUUID()).orElseThrow(()->new Exception(ProjectExceptionMessage.PROJECT_NOT_FOUND.getMessage()));
        projectEntity.setModifier(userEntity);
        projectEntity.setProjectName(request.getProjectName());
        projectEntity.setProjectDescription(request.getProjectDescription());
        projectEntity.setProjectStartTimestamp(request.getProjectStartTime());
        projectEntity.setProjectEndTimestamp(request.getProjectEndTime());
        projectEntity.setDonationGoal(request.getDonationGoal());
        projectRepository.save(projectEntity);
    }

    public void delete(CustomUserDetails user, ProjectDeleteRequest request) {
        projectRepository.deleteById(request.getProjectUUID());
    }
}
