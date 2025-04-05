package com.nonilab.model.response;

import com.nonilab.dao.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String username;
    String mail;
    String password;
    String gender;
    String language;
    String country;
    Date birthday;
    Integer age;
    String profile_picture;
    Set<RoleResponse> roles;
}
