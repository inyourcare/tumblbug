package com.kkh.app.request.project;

import lombok.Data;

import java.util.UUID;

@Data
public class ProjectDeleteRequest {
    UUID projectUUID;
}
