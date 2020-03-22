package com.kkh.app.jpa.entity;

import lombok.AccessLevel;
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
    @Size(max = 20)
    private int userId;

    @Column(unique=true)
    @NotBlank
    @Size(max = 80)
    private String loginId;

    @NotBlank
    @Size(max = 80)
    private String password;
}
