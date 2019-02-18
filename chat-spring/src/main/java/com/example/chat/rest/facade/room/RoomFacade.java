package com.example.chat.rest.facade.room;

import com.example.chat.rest.model.room.request.CreateMessageInRoomRequest;
import com.example.chat.rest.model.room.request.CreateRoomRequest;
import com.example.chat.rest.model.room.request.UpdateRoomRequest;
import com.example.chat.rest.model.room.response.*;
import com.example.chat.security.model.local.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface RoomFacade {
    ResponseEntity<GetRoomsResponse> getRooms(UserPrincipal principal, int page, int size);

    ResponseEntity<CreateRoomResponse> createRoom(CreateRoomRequest request);

    ResponseEntity<GetRoomResponse> getRoomById(Long id);

    ResponseEntity<UpdateRoomResponse> updateRoomById(Long Id, UpdateRoomRequest request);

    ResponseEntity<DeleteRoomResponse> deleteRoom(Long id);

    ResponseEntity<GetRoomMessagesResponse> getRoomMessages(UserPrincipal principal, Long id, int page, int size);

    ResponseEntity<GetRoomNewMessagesResponse> getRoomNewMessages(UserPrincipal principal, Long id);

    ResponseEntity<CreateMessageResponse> createMessageInRoom(UserPrincipal principal, Long id, CreateMessageInRoomRequest request);

    ResponseEntity<JoinRoomResponse> joinRoom(UserPrincipal userPrincipal, Long id);

    ResponseEntity<LeaveRoomResponse> leaveRoom(UserPrincipal userPrincipal, Long id);

    ResponseEntity<KickUserFromRoomResponse> removeUserFromRoom(String username, Long roomId);

    ResponseEntity<DeleteMessageResponse> deleteMessageById(Long messageId);
}
