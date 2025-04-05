package com.nonilab.mapper;

import com.nonilab.dao.entity.RoleEntity;
import com.nonilab.model.response.RoleResponse;

public enum RoleMapper {
    ROLE_MAPPER;

    public RoleEntity toRoleEntity(String role) {
        return RoleEntity.builder()
                .authority(role)
                .build();
    }

    public RoleResponse toRoleResponse(RoleEntity entity) {
        return RoleResponse.builder()
                .authority(entity.getAuthority())
                .build();
    }
}
