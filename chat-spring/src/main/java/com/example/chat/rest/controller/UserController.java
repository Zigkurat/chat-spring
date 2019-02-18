package com.example.chat.rest.controller;

import com.example.chat.rest.constant.RestConstants;
import com.example.chat.rest.facade.user.UserFacade;
import com.example.chat.rest.model.user.request.CreateUserRequest;
import com.example.chat.rest.model.user.request.UpdateCurrentUserRequest;
import com.example.chat.rest.model.user.request.UpdateUserByIdRequest;
import com.example.chat.rest.model.user.response.*;
import com.example.chat.security.model.local.UserPrincipal;
import com.example.chat.security.annotation.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserFacade userFacade;

    public UserController(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserResponse> getCurrentUser(@CurrentUser final UserPrincipal principal) {
        return userFacade.getCurrentUser(principal);
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateUserResponse> updateCurrentUser(@CurrentUser final UserPrincipal principal, @Valid @RequestBody final UpdateCurrentUserRequest request) {
        return userFacade.updateCurrentUser(principal, request);
    }

    @GetMapping("/")
    public ResponseEntity<GetUsersResponse> getUsers(@RequestParam(value = "page", defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) final int page,
                                                     @RequestParam(value = "size", defaultValue = RestConstants.DEFAULT_PAGE_SIZE) final int size) {
        return userFacade.getUsers(page, size);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody final CreateUserRequest request) {
        return userFacade.createUser(request);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("userId") final Long userId) {
        return userFacade.getUserById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable("userId") final Long userId, @Valid @RequestBody final UpdateUserByIdRequest request) {
        return userFacade.updateUserById(userId, request);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeleteUserByIdResponse> deleteUserById(@PathVariable("userId") final Long userId) {
        return userFacade.deleteUserById(userId);
    }
}
