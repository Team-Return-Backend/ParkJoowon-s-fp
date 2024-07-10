package com.example.jobis2.domain.auth.application;

import com.example.jobis2.domain.auth.dto.request.LoginRequest;
import com.example.jobis2.domain.auth.dto.response.LoginResponse;
import com.example.jobis2.domain.user.dao.UserRepository;
import com.example.jobis2.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) {
        var user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getAccountId());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredAt(jwtTokenProvider.getAccessTokenExpiryDate())
                .refreshExpiredAt(jwtTokenProvider.getRefreshTokenExpiryDate())
                .part(user.getPart())
                .build();
    }
}
