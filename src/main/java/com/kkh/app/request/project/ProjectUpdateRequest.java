package com.kkh.app.request.project;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ProjectUpdateRequest {
    UUID projectUUID;
    String projectName;
    String projectDescription;
    long donationGoal;
    Date projectStartTime;
    Date projectEndTime;
}
