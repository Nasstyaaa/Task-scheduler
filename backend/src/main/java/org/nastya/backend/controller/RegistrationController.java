package org.nastya.backend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.nastya.backend.dto.RegistrationRequest;
import org.nastya.backend.dto.RegistrationResponse;
import org.nastya.backend.exception.UserAlreadyExistsException;
import org.nastya.backend.model.User;
import org.nastya.backend.repository.UserRepository;
import org.nastya.backend.security.CustomUserDetails;
import org.nastya.backend.security.jwt.JwtIssuer;
import org.nastya.backend.service.RegistrationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final JwtIssuer jwtIssuer;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("errors", errors));
            }

            RegistrationResponse response = registrationService.register(request);
            String jwt = jwtIssuer.issue(response.getId(), response.getUsername(), response.getEmail());
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                    .build();

        } catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
                "id", principal.getId(),
                "username", principal.getUsername(),
                "email", principal.getEmail()
        ));
    }
}
