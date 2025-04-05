package com.nonilab.mapper;

import com.nonilab.model.jwt.AccessTokenClaimsSet;
import com.nonilab.model.jwt.AuthPayloadDto;
import com.nonilab.model.jwt.RefreshTokenClaimsSet;

import java.util.Date;

import static com.nonilab.model.constants.AuthConstant.ISSUER;

public enum TokenMapper {
    TOKEN_MAPPER;

    public AccessTokenClaimsSet buildAccessTokenClaimsSet(AuthPayloadDto dto, Date expirationTime) {
        return AccessTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .createdTime(new Date())
                .expirationTime(expirationTime)
                .build();
    }

    public RefreshTokenClaimsSet buildRefreshTokenClaimsSet(AuthPayloadDto dto,
                                                            int refreshTokenExpirationCount,
                                                            Date expirationTime) {
        return RefreshTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .expirationTime(expirationTime)
                .count(refreshTokenExpirationCount)
                .build();
    }
}
