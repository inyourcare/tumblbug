package com.kkh.app.request.project;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectRegisterRequest {
    String projectName;
    String projectDescription;
    long donationGoal;
    Date projectStartTime;
    Date projectEndTime;
}
