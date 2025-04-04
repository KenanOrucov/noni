package com.nonilab.service.concrete;

import com.nonilab.aop.annotation.Log;
import com.nonilab.model.jwt.AuthPayloadDto;
import com.nonilab.model.jwt.RefreshTokenRequest;
import com.nonilab.model.request.LoginRequest;
import com.nonilab.model.response.LoginResponse;
import com.nonilab.service.abstraction.AuthenticationService;
import com.nonilab.service.abstraction.TokenService;
import com.nonilab.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Log
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AuthenticationServiceHandler implements AuthenticationService {
    AuthenticationManager authenticationManager;
    UserService userService;
    TokenService tokenService;

    public LoginResponse login(LoginRequest request) {
        authenticate(request);
        var user = userService.getUserByEmail(request.getEmail());
        return tokenService.generateToken(AuthPayloadDto.of(user.getId().toString(), user.getMail()));
    }

    public LoginResponse refresh(RefreshTokenRequest request) {
        return tokenService.refreshToken(request.getRefreshToken());
    }

    private void authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }
}
