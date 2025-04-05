package com.nonilab.service.abstraction;

import com.nonilab.model.jwt.RefreshTokenRequest;
import com.nonilab.model.request.LoginRequest;
import com.nonilab.model.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);

    LoginResponse refresh(RefreshTokenRequest request);
}
