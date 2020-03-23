package com.kkh.app.jpa.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Project")
@Data
public class ProjectEntity {

    @Id
    private UUID projectUUID;

    @Size(max = 200)
    private String projectName;

    @Size(max = 1200)
    private String projectDescription;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "modifierId")
    private UserEntity modifier;

    // 목표액
    private long donationGoal;
    // 후원수
    private int donationCount;
    // 후원액
    private long donationAccumulated;

    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date projectStartTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date projectEndTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTimestamp;

}
