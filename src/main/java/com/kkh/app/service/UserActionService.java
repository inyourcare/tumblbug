package com.kkh.app.service;

import com.kkh.app.exception.ProjectExceptionMessage;
import com.kkh.app.jpa.entity.ProjectEntity;
import com.kkh.app.jpa.repo.ProjectRepository;
import com.kkh.app.request.useraction.UserActionRequest;
import com.kkh.app.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActionService {
    @Autowired
    ProjectRepository projectRepository;

    public void supportProject(CustomUserDetails user, UserActionRequest request) throws Exception {
        ProjectEntity projectEntity = projectRepository.findById(request.getProjectUUID()).orElseThrow(()->new Exception(ProjectExceptionMessage.PROJECT_NOT_FOUND.getMessage()));
        projectEntity.setDonationCount(projectEntity.getDonationCount()+1);
        projectEntity.setDonationAccumulated(projectEntity.getDonationAccumulated() + request.getDonationMoney());
        projectRepository.save(projectEntity);
    }
}
