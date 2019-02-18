package com.example.chat.service.room;

import com.example.chat.persistence.entity.room.Room;
import com.example.chat.service.room.dto.CreateRoomDto;
import com.example.chat.service.room.dto.UpdateRoomDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {

    Page<Room> getRooms(int page, int size);

    List<Room> getRoomsForUserId(Long userId);

    Room createRoom(CreateRoomDto dto);

    Room updateRoom(UpdateRoomDto dto);

    Room getRoomById(Long id);

    boolean deleteRoomById(Long id);

    boolean addUserToRoom(Long userId, Long roomId);

    boolean removeUserFromRoom(Long userId, Long roomId);

    boolean removeUserFromRoom(String username, Long roomId);
}
