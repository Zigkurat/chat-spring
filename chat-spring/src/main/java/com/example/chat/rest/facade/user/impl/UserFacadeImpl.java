package com.example.chat.rest.facade.user.impl;

import com.example.chat.persistence.entity.user.User;
import com.example.chat.rest.facade.user.UserFacade;
import com.example.chat.rest.model.user.request.CreateUserRequest;
import com.example.chat.rest.model.user.request.UpdateCurrentUserRequest;
import com.example.chat.rest.model.user.request.UpdateUserByIdRequest;
import com.example.chat.rest.model.user.response.*;
import com.example.chat.security.model.local.UserPrincipal;
import com.example.chat.service.user.UserService;
import com.example.chat.service.user.dto.CreateUserDto;
import com.example.chat.service.user.dto.UpdateUserDto;
import com.example.chat.service.user.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacadeImpl(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<GetUserResponse> getCurrentUser(final UserPrincipal principal) {
        final User user = userService.getUserById(principal.getId());

        final GetUserResponse response = userMapper.map(user, GetUserResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UpdateUserResponse> updateCurrentUser(final UserPrincipal principal, final UpdateCurrentUserRequest request) {
        final UpdateUserDto dto = userMapper.map(request, UpdateUserDto.class);
        dto.setId(principal.getId());
        userService.updateUser(dto);

        final UpdateUserResponse response = new UpdateUserResponse("Updated successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetUsersResponse> getUsers(final int page, final int size) {
        final Page<User> pagedUsers = userService.getUsers(page, size);

        final List<GetUserResponse> userResponses = userMapper.mapAsList(pagedUsers.getContent(), GetUserResponse.class);

        final GetUsersResponse response = new GetUsersResponse();
        response.setUsers(userResponses);
        response.setPage(pagedUsers.getNumber());
        response.setSize(pagedUsers.getSize());
        response.setTotalElements(pagedUsers.getTotalElements());
        response.setTotalPages(pagedUsers.getTotalPages());
        response.setLast(pagedUsers.isLast());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateUserResponse> createUser(final CreateUserRequest request) {
        final CreateUserDto dto = userMapper.map(request, CreateUserDto.class);
        userService.createUser(dto);

        final CreateUserResponse response = new CreateUserResponse("Created successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetUserResponse> getUserById(final Long id) {
        final User user = userService.getUserById(id);

        final GetUserResponse response = userMapper.map(user, GetUserResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UpdateUserResponse> updateUserById(final Long id, final UpdateUserByIdRequest request) {
        final UpdateUserDto dto = userMapper.map(request, UpdateUserDto.class);
        dto.setId(id);
        userService.updateUser(dto);

        final UpdateUserResponse response = new UpdateUserResponse("Updated successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DeleteUserByIdResponse> deleteUserById(final Long id) {
        userService.deleteUserById(id);

        final DeleteUserByIdResponse response = new DeleteUserByIdResponse("Deleted successfully.");
        return ResponseEntity.ok(response);
    }

}
