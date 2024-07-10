package com.example.jobis2.domain.auth.api;

import com.example.jobis2.domain.auth.application.LoginService;
import com.example.jobis2.domain.auth.application.ReissueService;
import com.example.jobis2.domain.auth.dto.request.LoginRequest;
import com.example.jobis2.domain.auth.dto.request.RefreshTokenRequest;
import com.example.jobis2.domain.auth.dto.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    private final ReissueService reissueService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/token")
    public LoginResponse reissue(@RequestBody @Valid RefreshTokenRequest request) {
        return reissueService.reissue(request);
    }
}
