package com.example.chat.rest.controller;

import com.example.chat.rest.facade.auth.AuthFacade;
import com.example.chat.rest.model.auth.request.SignInRequest;
import com.example.chat.rest.model.auth.request.SignUpRequest;
import com.example.chat.rest.model.auth.response.SignInResponse;
import com.example.chat.rest.model.auth.response.SignUpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthFacade authFacade;

    public AuthController(final AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> authenticateUser(@Valid @RequestBody final SignInRequest request) {
        return authFacade.authenticateUser(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> registerUser(@Valid @RequestBody final SignUpRequest request) {
        return authFacade.registerUser(request);
    }
}
