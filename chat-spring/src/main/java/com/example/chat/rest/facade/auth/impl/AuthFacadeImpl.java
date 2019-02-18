package com.example.chat.rest.facade.auth.impl;

import com.example.chat.persistence.entity.constant.AuthProvider;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.persistence.entity.constant.UserRole;
import com.example.chat.persistence.repository.UserRepository;
import com.example.chat.rest.facade.auth.AuthFacade;
import com.example.chat.rest.model.auth.request.SignInRequest;
import com.example.chat.rest.model.auth.request.SignUpRequest;
import com.example.chat.rest.model.auth.response.SignInResponse;
import com.example.chat.rest.model.auth.response.SignUpResponse;
import com.example.chat.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class AuthFacadeImpl implements AuthFacade {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthFacadeImpl(final JwtTokenProvider tokenProvider, final AuthenticationManager authenticationManager, final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<SignInResponse> authenticateUser(final SignInRequest request) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new SignInResponse(token));
    }

    @Override
    public ResponseEntity<SignUpResponse> registerUser(final SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SignUpResponse("Username already in use!"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SignUpResponse("Email Address already in use!"));
        }

        final User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setRole(UserRole.USER);
        user.setProvider(AuthProvider.LOCAL);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        final User result = userRepository.save(user);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new SignUpResponse("User registered successfully"));
    }
}
