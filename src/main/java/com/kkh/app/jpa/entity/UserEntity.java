package com.kkh.app.jpa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    private long userId;

    @Column(unique=true)
    @NotBlank
    @Size(max = 80)
    private String loginId;

    @NotBlank
    @Size(max = 80)
    private String password;

    @Size(max = 80)
    private String name;

    @Size(max = 80)
    @Column(unique=true)
    private String email;

    @Size(max = 11)
    private String phoneNo;

    @Builder
    public UserEntity(long userId, @NotBlank @Size(max = 80) String loginId, @NotBlank @Size(max = 80) String password) {
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
    }
}
