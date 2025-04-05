package com.nonilab.mapper;

import com.nonilab.dao.entity.RoleEntity;
import com.nonilab.dao.entity.UserEntity;
import com.nonilab.model.request.UserRequest;
import com.nonilab.model.response.UserResponse;

import java.util.Set;
import java.util.stream.Collectors;

import static com.nonilab.mapper.RoleMapper.ROLE_MAPPER;
import static com.nonilab.model.enums.UserStatus.ACTIVE;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity toUserEntity(UserRequest userRequest, RoleEntity authority) {
        return UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .mail(userRequest.getMail())
                .password(userRequest.getPassword())
                .gender(userRequest.getGender())
                .language(userRequest.getLanguage())
                .country(userRequest.getCountry())
                .birthday(userRequest.getBirthday())
                .userStatus(ACTIVE)
                .authorities(Set.of(authority))
                .build();
    }

    public UserResponse toUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .mail(userEntity.getMail())
                .password(userEntity.getPassword())
                .gender(userEntity.getGender())
                .language(userEntity.getLanguage())
                .country(userEntity.getCountry())
                .birthday(userEntity.getBirthday())
                .age(userEntity.getAge())
                .profile_picture(userEntity.getProfile_picture())
                .roles(userEntity.getAuthorities().stream().map(ROLE_MAPPER::toRoleResponse).collect(Collectors.toSet()))
                .build();
    }

    public void updateUser(UserRequest userRequest, UserEntity user, RoleEntity authority) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUsername(userRequest.getUsername());
        user.setMail(userRequest.getMail());
        user.setPassword(userRequest.getPassword());
        user.setGender(userRequest.getGender());
        user.setLanguage(userRequest.getLanguage());
        user.setCountry(userRequest.getCountry());
        user.setBirthday(userRequest.getBirthday());
        user.setAuthorities(Set.of(authority));
    }

    public void addAuthority(UserEntity user, RoleEntity role) {
        user.getAuthorities().add(role);
    }
}
