package com.example.chat.service.user.impl;

import com.example.chat.persistence.entity.constant.UserRole;
import com.example.chat.persistence.entity.user.RoomLastMessage;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.persistence.repository.RoomLastMessageRepository;
import com.example.chat.persistence.repository.UserRepository;
import com.example.chat.service.user.UserService;
import com.example.chat.service.user.dto.CreateUserDto;
import com.example.chat.service.user.dto.UpdateUserDto;
import com.example.chat.service.user.exception.EmailAlreadyInUseException;
import com.example.chat.service.user.exception.UserNotFoundForIdException;
import com.example.chat.service.user.exception.UserNotFoundForUsernameException;
import com.example.chat.service.user.exception.UsernameAlreadyInUseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoomLastMessageRepository roomLastMessageRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, RoomLastMessageRepository roomLastMessageRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roomLastMessageRepository = roomLastMessageRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<User> getUsers(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "username");
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User createUser(final CreateUserDto createUserDto) {
        throwIfUsernameOrEmailIsInUse(createUserDto.getUsername(), createUserDto.getEmail());

        final User user = userFromCreateUserDto(createUserDto);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundForIdException(id));
    }

    @Override
    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundForUsernameException(username));
    }

    @Override
    @Transactional
    public User updateUser(final UpdateUserDto updateUserDto) {
        throwIfUsernameOrEmailIsInUse(updateUserDto.getUsername(), updateUserDto.getEmail());

        final User user = userRepository.findById(updateUserDto.getId())
                .orElseThrow(() -> new UserNotFoundForIdException(updateUserDto.getId()));
        updateUserFromUpdateUserDto(user, updateUserDto);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean deleteUserById(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundForIdException(id));
        userRepository.delete(user);

        return true;
    }

    @Override
    public RoomLastMessage createOrUpdateRoomLastMessage(RoomLastMessage roomLastMessage) {
        return roomLastMessageRepository.save(roomLastMessage);
    }

    private void throwIfUsernameOrEmailIsInUse(final String username, final String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyInUseException(username);
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }
    }

    private User userFromCreateUserDto(final CreateUserDto dto) {
        final User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.valueOf(dto.getRole()));

        return user;
    }

    private void updateUserFromUpdateUserDto(final User user, final UpdateUserDto dto) {
        if (!StringUtils.isEmpty(dto.getUsername())) {
            user.setUsername(dto.getUsername());
        }
        if (!StringUtils.isEmpty(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (!StringUtils.isEmpty(dto.getPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (!StringUtils.isEmpty(dto.getRole())) {
            user.setRole(UserRole.valueOf(dto.getRole()));
        }
        if (!StringUtils.isEmpty(dto.getAvatarUrl())) {
            user.setAvatarUrl(dto.getAvatarUrl());
        }
    }
}
