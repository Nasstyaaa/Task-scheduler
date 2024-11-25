package org.nastya.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nastya.backend.security.jwt.JwtIssuer;
import org.nastya.backend.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtIssuer jwtIssuer;
    private final CustomUserDetailsService customUserDetailsService;


}
