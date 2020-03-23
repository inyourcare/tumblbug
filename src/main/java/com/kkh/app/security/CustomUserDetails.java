package com.kkh.app.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkh.app.jpa.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class CustomUserDetails implements UserDetails {

    private String loginId;

    @JsonIgnore
    private String userId;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public CustomUserDetails(UserEntity userEntity) {
        this.userId = String.valueOf(userEntity.getUserId());
        this.loginId = userEntity.getLoginId();
        this.password = userEntity.getPassword();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(SecurityCode.NORMAL.getCode()));
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
