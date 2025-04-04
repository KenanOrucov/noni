package com.nonilab.model.userdetails;

import com.nonilab.dao.entity.UserEntity;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class CustomUserDetails implements UserDetails {
    String username;
    String password;
    boolean isAccountNonExpired;
    boolean isAccountNonLocked;
    boolean isCredentialsNonExpired;
    boolean isEnabled;
    Set<CustomGrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user, Set<CustomGrantedAuthority> authorities) {
        this.username = user.getMail();
        this.password = user.getPassword();
        this.isAccountNonExpired = !user.getIsAccountBlocked();
        this.isAccountNonLocked = !user.getIsAccountLocked();
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.authorities = authorities;
    }
}