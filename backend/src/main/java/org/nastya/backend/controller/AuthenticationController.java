package org.nastya.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nastya.backend.security.jwt.JwtIssuer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtIssuer jwtIssuer;

}
