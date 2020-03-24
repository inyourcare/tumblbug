package com.kkh.app.response.project;

import com.kkh.app.jpa.entity.ProjectEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectGetDetailResponse {
    Date projectEndTimestamp;
    Date projectStartTimestamp;
    String state;
    long donationAccumulated;
    int donationCount;
    long donationGoal;
    String creatorEmail;
    String creatorName;
    String creatorPhoneNo;
    String projectName;
    String projectDescription;
    Date registeredTimestamp;
    Date updatedTimestamp;

    @Builder
    ProjectGetDetailResponse(ProjectEntity projectEntity) {
        this.projectEndTimestamp = projectEntity.getProjectEndTimestamp();
        this.projectStartTimestamp = projectEntity.getProjectStartTimestamp();
        this.state = projectEntity.getState();
        this.donationAccumulated = projectEntity.getDonationAccumulated();
        this.donationCount = projectEntity.getDonationCount();
        this.donationGoal = projectEntity.getDonationGoal();
        this.creatorName = projectEntity.getCreator().getName();
        this.creatorEmail = projectEntity.getCreator().getEmail();
        this.creatorPhoneNo = projectEntity.getCreator().getPhoneNo();
        this.projectName = projectEntity.getProjectName();
        this.projectDescription = projectEntity.getProjectDescription();
        this.registeredTimestamp = projectEntity.getRegisteredTimestamp();
        this.updatedTimestamp = projectEntity.getUpdatedTimestamp();
    }
}
