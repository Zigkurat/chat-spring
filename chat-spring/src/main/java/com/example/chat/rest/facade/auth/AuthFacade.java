package com.example.chat.rest.facade.auth;

import com.example.chat.rest.model.auth.request.SignInRequest;
import com.example.chat.rest.model.auth.request.SignUpRequest;
import com.example.chat.rest.model.auth.response.SignInResponse;
import com.example.chat.rest.model.auth.response.SignUpResponse;
import org.springframework.http.ResponseEntity;

public interface AuthFacade {
    ResponseEntity<SignInResponse> authenticateUser(SignInRequest request);

    ResponseEntity<SignUpResponse> registerUser(SignUpRequest request);
}
