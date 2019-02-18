package com.example.chat.rest.facade.user;

import com.example.chat.rest.model.user.request.CreateUserRequest;
import com.example.chat.rest.model.user.request.UpdateCurrentUserRequest;
import com.example.chat.rest.model.user.request.UpdateUserByIdRequest;
import com.example.chat.rest.model.user.response.*;
import com.example.chat.security.model.local.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface UserFacade {

    ResponseEntity<GetUserResponse> getCurrentUser(UserPrincipal principal);

    ResponseEntity<UpdateUserResponse> updateCurrentUser(UserPrincipal principal, UpdateCurrentUserRequest request);

    ResponseEntity<GetUsersResponse> getUsers(int page, int size);

    ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request);

    ResponseEntity<GetUserResponse> getUserById(Long id);

    ResponseEntity<UpdateUserResponse> updateUserById(Long id, UpdateUserByIdRequest request);

    ResponseEntity<DeleteUserByIdResponse> deleteUserById(Long id);
}
