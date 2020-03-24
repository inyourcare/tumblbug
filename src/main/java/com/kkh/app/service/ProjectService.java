package com.kkh.app.service;

import com.kkh.app.code.ProjectCode;
import com.kkh.app.exception.ProjectExceptionMessage;
import com.kkh.app.exception.UserExceptionMessage;
import com.kkh.app.jpa.entity.ProjectEntity;
import com.kkh.app.jpa.entity.UserEntity;
import com.kkh.app.jpa.repo.ProjectRepository;
import com.kkh.app.jpa.repo.UserRepository;
import com.kkh.app.request.project.*;
import com.kkh.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;


    @Transactional(rollbackFor = Exception.class)
    public void register(CustomUserDetails user, ProjectRegisterRequest request) throws Exception {
        UserEntity userEntity = userRepository.findById(Long.parseLong(user.getUserId())).orElseThrow(()->new Exception(UserExceptionMessage.USER_NOT_FOUND.getMessage()));
        Date now = new Date();
        ProjectEntity projectEntity = ProjectEntity.builder()
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
                .build();
        projectEntity.setState();
        projectRepository.save(projectEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CustomUserDetails user, ProjectUpdateRequest request) throws Exception {
        UserEntity userEntity = userRepository.findById(Long.parseLong(user.getUserId())).orElseThrow(()->new Exception(UserExceptionMessage.USER_NOT_FOUND.getMessage()));
        Date now = new Date();
        ProjectEntity projectEntity = projectRepository.findById(request.getProjectUUID()).orElseThrow(()->new Exception(ProjectExceptionMessage.PROJECT_NOT_FOUND.getMessage()));
        projectEntity.setModifier(userEntity);
        projectEntity.setUpdatedTimestamp(now);
        projectEntity.setProjectName(request.getProjectName());
        projectEntity.setProjectDescription(request.getProjectDescription());
        projectEntity.setProjectStartTimestamp(request.getProjectStartTime());
        projectEntity.setProjectEndTimestamp(request.getProjectEndTime());
        projectEntity.setDonationGoal(request.getDonationGoal());
        projectRepository.save(projectEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(CustomUserDetails user, ProjectDeleteRequest request) throws Exception {
        if(!projectRepository.existsById(request.getProjectUUID()))
            throw new Exception(ProjectExceptionMessage.PROJECT_NOT_FOUND.getMessage());
        projectRepository.deleteById(request.getProjectUUID());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ProjectEntity> getList(CustomUserDetails user, ProjectGetListRequest request) {
        return projectRepository.saveAll(projectRepository.findAll(Sort.by(Sort.Direction.valueOf(request.getSortDirection()) , request.getSort())).stream().peek(ProjectEntity::setState).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = Exception.class)
    public ProjectEntity getDetail(CustomUserDetails user, ProjectGetDetailRequest request) throws Exception {
        ProjectEntity projectEntity = projectRepository.findById(request.getProjectUUID()).orElseThrow(()-> new Exception(ProjectExceptionMessage.PROJECT_NOT_FOUND.getMessage()));
        projectEntity.setState();
        projectRepository.save(projectEntity);
        return projectEntity;
    }
}
