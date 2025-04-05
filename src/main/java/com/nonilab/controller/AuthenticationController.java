package com.nonilab.controller;

import com.nonilab.model.jwt.RefreshTokenRequest;
import com.nonilab.model.request.LoginRequest;
import com.nonilab.model.response.LoginResponse;
import com.nonilab.service.abstraction.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@RequestBody RefreshTokenRequest request) {
        return authenticationService.refresh(request);
    }
}
