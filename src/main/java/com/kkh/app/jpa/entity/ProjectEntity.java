package com.kkh.app.jpa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
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

    @Size(max = 1)
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date projectStartTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date projectEndTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTimestamp;

    @Builder
    public ProjectEntity(UUID projectUUID, @Size(max = 200) String projectName, @Size(max = 1200) String projectDescription, UserEntity creator, UserEntity modifier, long donationGoal, int donationCount, long donationAccumulated, String state, Date projectStartTimestamp, Date projectEndTimestamp, Date registeredTimestamp, Date updatedTimestamp) {
        this.projectUUID = projectUUID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.creator = creator;
        this.modifier = modifier;
        this.donationGoal = donationGoal;
        this.donationCount = donationCount;
        this.donationAccumulated = donationAccumulated;
        this.state = state;
        this.projectStartTimestamp = projectStartTimestamp;
        this.projectEndTimestamp = projectEndTimestamp;
        this.registeredTimestamp = registeredTimestamp;
        this.updatedTimestamp = updatedTimestamp;
    }
}
