package com.example.chat.service.room.impl;

import com.example.chat.persistence.entity.room.Room;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.persistence.repository.RoomRepository;
import com.example.chat.persistence.repository.UserRepository;
import com.example.chat.service.room.RoomService;
import com.example.chat.service.room.dto.CreateRoomDto;
import com.example.chat.service.room.dto.UpdateRoomDto;
import com.example.chat.service.room.exception.RoomNameAlreadyInUseException;
import com.example.chat.service.room.exception.RoomNotFoundForIdException;
import com.example.chat.service.user.exception.UserNotFoundForIdException;
import com.example.chat.service.user.exception.UserNotFoundForUsernameException;
import com.example.chat.service.user.exception.UserNotFoundInRoomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomServiceImpl(final RoomRepository roomRepository, final UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<Room> getRooms(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "name");
        return roomRepository.findAll(pageable);
    }

    @Override
    public List<Room> getRoomsForUserId(final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundForIdException(userId));

        return roomRepository.findAllByUsersContaining(user);
    }

    @Override
    @Transactional
    public Room createRoom(final CreateRoomDto dto) {
        if (roomRepository.existsByName(dto.getName())) {
            throw new RoomNameAlreadyInUseException(dto.getName());
        }

        final Room room = roomFromCreateRoomDto(dto);

        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public Room updateRoom(final UpdateRoomDto dto) {
        if (roomRepository.existsByName(dto.getName())) {
            throw new RoomNameAlreadyInUseException(dto.getName());
        }
        final Room room = roomRepository.findById(dto.getId())
                .orElseThrow(() -> new RoomNotFoundForIdException(dto.getId()));

        room.setName(dto.getName());

        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundForIdException(id));
    }

    @Override
    @Transactional
    public boolean deleteRoomById(final Long id) {
        final Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundForIdException(id));

        roomRepository.delete(room);

        return true;
    }

    @Override
    @Transactional
    public boolean addUserToRoom(final Long userId, final Long roomId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundForIdException(userId));
        final Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundForIdException(roomId));

        room.getUsers().add(user);
        roomRepository.save(room);
        return true;
    }

    @Override
    @Transactional
    public boolean removeUserFromRoom(final Long userId, final Long roomId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundForIdException(userId));
        final Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundForIdException(roomId));

        if (!room.getUsers().contains(user)) {
            throw new UserNotFoundInRoomException(userId, roomId);
        }

        room.getUsers().remove(user);
        roomRepository.save(room);
        return true;
    }

    @Override
    @Transactional
    public boolean removeUserFromRoom(final String username, final Long roomId) {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundForUsernameException(username));
        final Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundForIdException(roomId));

        if (!room.getUsers().contains(user)) {
            throw new UserNotFoundInRoomException(username, roomId);
        }

        room.getUsers().remove(user);
        roomRepository.save(room);
        return true;
    }

    private Room roomFromCreateRoomDto(final CreateRoomDto dto) {
        final Room room = new Room();
        room.setName(dto.getName());

        return room;
    }
}
