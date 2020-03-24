package com.kkh.app.request.project;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ProjectGetListRequest {
    @ApiModelProperty(notes = "정렬컬럼", example = "projectStartTimestamp / projectEndTimestamp / donationGoal / donationAccumulated", required = true, position = 0)
    String sort;

    @ApiModelProperty(notes = "정렬방향", example = "DESC / ASC", required = true, position = 0)
    String sortDirection;
}
