package com.nonilab.service.concrete;

import com.nonilab.aop.annotation.Log;
import com.nonilab.aop.annotation.LogIgnore;
import com.nonilab.exception.AuthenticationException;
import com.nonilab.model.jwt.AuthPayloadDto;
import com.nonilab.model.response.LoginResponse;
import com.nonilab.service.abstraction.TokenService;
import com.nonilab.util.JwtUtil;
import com.nonilab.util.KeyUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

import static com.nonilab.mapper.TokenMapper.TOKEN_MAPPER;
import static com.nonilab.model.constants.ExceptionConstants.*;
import static lombok.AccessLevel.PRIVATE;

@Log
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TokenServiceHandler implements TokenService {
    final JwtUtil jwtUtil;
    @Value("${jwt.access-token.expiration.time}")
    int accessTokenExpirationTime;
    @Value("${jwt.refresh-token.expiration.time}")
    int refreshTokenExpirationTime;

    @SneakyThrows
    public LoginResponse generateToken(AuthPayloadDto dto) {
        final int refreshTokenExpirationCount = 50;
        try {
            PrivateKey privateKey = KeyUtils.loadPrivateKey("src/main/resources/privateKey.pem");

            var accessTokenClaimsSet = TOKEN_MAPPER.buildAccessTokenClaimsSet(
                    dto,
                    jwtUtil.generateSessionExpirationTime(accessTokenExpirationTime)
            );
            var refreshTokenClaimsSet = TOKEN_MAPPER.buildRefreshTokenClaimsSet(
                    dto,
                    refreshTokenExpirationCount,
                    jwtUtil.generateSessionExpirationTime(refreshTokenExpirationTime)
            );
            var accessToken = jwtUtil.generateToken(accessTokenClaimsSet, privateKey);
            var refreshToken = jwtUtil.generateToken(refreshTokenClaimsSet, privateKey);

            return LoginResponse.of(accessToken, refreshToken);
        } catch (Exception e) {
            log.error("Error generating token", e);
            throw new AuthenticationException(USER_UNAUTHORIZED, 401);
        }
    }

    @LogIgnore
    public LoginResponse refreshToken(String refreshToken) {
        var refreshTokenClaimsSet = jwtUtil.getClaimsFromRefreshToken(refreshToken);
        var userId = refreshTokenClaimsSet.getUserId();
        var username = refreshTokenClaimsSet.getUsername();
        try {
            PublicKey publicKey = KeyUtils.loadPublicKey("src/main/resources/publicKey.pem");

            jwtUtil.verifyToken(refreshToken, (RSAPublicKey) publicKey);

            if (jwtUtil.isRefreshTokenTimeExpired(refreshTokenClaimsSet)) {
                throw new AuthenticationException(REFRESH_TOKEN_EXPIRED, 401);
            }
            if (jwtUtil.isRefreshTokenCountExpired(refreshTokenClaimsSet)) {
                throw new AuthenticationException(REFRESH_TOKEN_COUNT_EXPIRED, 401);
            }

            return generateToken(AuthPayloadDto.of(userId, username));
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AuthenticationException(USER_UNAUTHORIZED, 401);
        }
    }


    @LogIgnore
    public AuthPayloadDto validateToken(String accessToken) {
        try {
            var userId = jwtUtil.getClaimsFromAccessToken(accessToken).getUserId();
            var username = jwtUtil.getClaimsFromAccessToken(accessToken).getUsername();

            PublicKey publicKey = KeyUtils.loadPublicKey("src/main/resources/publicKey.pem");

            jwtUtil.verifyToken(accessToken, (RSAPublicKey) publicKey);

            return AuthPayloadDto.of(userId, username);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error validating token: ", ex);
            throw new AuthenticationException(USER_UNAUTHORIZED, 401);
        }
    }
}