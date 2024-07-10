package com.example.jobis2.domain.auth.dto.response;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class LoginResponse {

    private String accessToken;

    private String refreshToken;

    private Date accessExpiredAt;

    private Date refreshExpiredAt;

    private Part part;
}
