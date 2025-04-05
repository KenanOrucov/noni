package com.nonilab.service.abstraction;

import com.nonilab.model.jwt.AuthPayloadDto;
import com.nonilab.model.response.LoginResponse;

public interface TokenService {
    LoginResponse generateToken(AuthPayloadDto dto);

    LoginResponse refreshToken(String refreshToken);

    AuthPayloadDto validateToken(String accessToken);
}
