package com.kkh.app.jpa.repo;

import com.kkh.app.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity , Integer> {
    Optional<UserEntity> findByLoginId(String loginId);
}
