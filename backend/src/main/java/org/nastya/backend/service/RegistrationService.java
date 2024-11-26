package org.nastya.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nastya.backend.dto.RegistrationRequest;
import org.nastya.backend.dto.RegistrationResponse;
import org.nastya.backend.exception.UserAlreadyExistsException;
import org.nastya.backend.model.User;
import org.nastya.backend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        User user = new ModelMapper().map(registrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            User registredUser = userRepository.save(user);
            return new ModelMapper().map(registredUser, RegistrationResponse.class);

        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException();
        }
    }
}
