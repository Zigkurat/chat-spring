package com.example.chat.service.user;

import com.example.chat.persistence.entity.user.UserLastSeenMessageInRoom;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.service.user.dto.CreateUserDto;
import com.example.chat.service.user.dto.UpdateUserDto;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getUsers(int page, int size);

    User createUser(CreateUserDto dto);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User updateUser(UpdateUserDto dto);

    boolean deleteUserById(Long id);

    UserLastSeenMessageInRoom createOrUpdateUserLastSeenMessageInRoom(UserLastSeenMessageInRoom userLastSeenMessageInRoom);
}
