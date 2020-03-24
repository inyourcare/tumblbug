package com.kkh.app.response.project;

import com.kkh.app.jpa.entity.ProjectEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectGetListResponse {
    List<Project> projectList;

    @Builder
    ProjectGetListResponse(List<ProjectEntity> projectEntityList) {
        projectList = projectEntityList.stream().map(Project::new).collect(Collectors.toList());
    }

    @Data
    private class Project {

        String projectName;
        String creator;
        long donationGoal;
        int donationCount;
        long donationAccumulated;
        String state;
        Date projectStartTimestamp;
        Date projectEndTimestamp;

        Project(ProjectEntity projectEntity) {
            this.projectName = projectEntity.getProjectName();
            this.creator = projectEntity.getCreator().getName();
            this.donationGoal = projectEntity.getDonationGoal();
            this.donationCount = projectEntity.getDonationCount();
            this.donationAccumulated = projectEntity.getDonationAccumulated();
            this.state = projectEntity.getState();
            this.projectStartTimestamp = projectEntity.getProjectStartTimestamp();
            this.projectEndTimestamp = projectEntity.getProjectEndTimestamp();
        }
    }
}
