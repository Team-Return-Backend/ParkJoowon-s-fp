package com.example.jobis2.domain.auth.application;

import com.example.jobis2.domain.auth.dao.RefreshTokenRepository;
import com.example.jobis2.domain.auth.domain.RefreshToken;
import com.example.jobis2.domain.auth.dto.request.RefreshTokenRequest;
import com.example.jobis2.domain.auth.dto.response.LoginResponse;
import com.example.jobis2.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginResponse reissue(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

        return jwtTokenProvider.receiveToken(refreshToken.getAccountId());
    }
}
