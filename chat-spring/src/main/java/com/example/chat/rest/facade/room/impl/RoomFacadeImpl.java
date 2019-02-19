package com.example.chat.rest.facade.room.impl;

import com.example.chat.persistence.entity.message.Message;
import com.example.chat.persistence.entity.room.Room;
import com.example.chat.persistence.entity.user.UserLastSeenMessageInRoom;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.rest.facade.room.RoomFacade;
import com.example.chat.rest.model.room.request.CreateMessageInRoomRequest;
import com.example.chat.rest.model.room.request.CreateRoomRequest;
import com.example.chat.rest.model.room.request.UpdateRoomRequest;
import com.example.chat.rest.model.room.response.*;
import com.example.chat.security.model.local.UserPrincipal;
import com.example.chat.service.badword.BadWordService;
import com.example.chat.service.message.MessageService;
import com.example.chat.service.message.dto.CreateMessageDto;
import com.example.chat.service.message.mapper.MessageMapper;
import com.example.chat.service.room.RoomService;
import com.example.chat.service.room.dto.CreateRoomDto;
import com.example.chat.service.room.dto.UpdateRoomDto;
import com.example.chat.service.room.mapper.RoomMapper;
import com.example.chat.service.user.UserService;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomFacadeImpl implements RoomFacade {

    private final RoomService roomService;
    private final MessageService messageService;
    private final BadWordService badWordService;
    private final UserService userService;
    private final RoomMapper roomMapper;
    private final MessageMapper messageMapper;

    public RoomFacadeImpl(final RoomService roomService, MessageService messageService, BadWordService badWordService, UserService userService, final RoomMapper roomMapper, MessageMapper messageMapper) {
        this.roomService = roomService;
        this.messageService = messageService;
        this.badWordService = badWordService;
        this.userService = userService;
        this.roomMapper = roomMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public ResponseEntity<GetRoomsResponse> getRooms(final UserPrincipal principal, final int page, final int size) {
        final User user = userService.getUserById(principal.getId());
        final Page<Room> pagedRooms = roomService.getRooms(page, size);
        final List<GetRoomResponse> roomResponses = new ArrayList<>();

        pagedRooms.getContent().forEach(room -> {
            GetRoomResponse response = roomMapper.map(room, GetRoomResponse.class);
            if (room.getUsers().contains(user)) {
                response.setJoined(true);
            }
            roomResponses.add(response);
        });
        final GetRoomsResponse response = new GetRoomsResponse();
        response.setRooms(roomResponses);
        response.setPage(pagedRooms.getNumber());
        response.setSize(pagedRooms.getSize());
        response.setTotalElements(pagedRooms.getTotalElements());
        response.setTotalPages(pagedRooms.getTotalPages());
        response.setLast(pagedRooms.isLast());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateRoomResponse> createRoom(final CreateRoomRequest request) {
        final CreateRoomDto dto = roomMapper.map(request, CreateRoomDto.class);
        roomService.createRoom(dto);

        final CreateRoomResponse response = new CreateRoomResponse("Created successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetRoomResponse> getRoomById(Long id) {
        Room room = roomService.getRoomById(id);

        GetRoomResponse getRoomResponse = roomMapper.map(room, GetRoomResponse.class);
        return ResponseEntity.ok(getRoomResponse);
    }

    @Override
    public ResponseEntity<UpdateRoomResponse> updateRoomById(final Long id, final UpdateRoomRequest request) {
        final UpdateRoomDto dto = roomMapper.map(request, UpdateRoomDto.class);
        dto.setId(id);
        roomService.updateRoom(dto);

        final UpdateRoomResponse response = new UpdateRoomResponse("Updated successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DeleteRoomResponse> deleteRoom(final Long id) {
        roomService.deleteRoomById(id);

        final DeleteRoomResponse response = new DeleteRoomResponse("Deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetRoomMessagesResponse> getRoomMessages(final UserPrincipal principal, final Long id, final int page, final int size) {
        final User user = userService.getUserById(principal.getId());
        final Room room = roomService.getRoomById(id);
        if (!room.getUsers().contains(user)) {
            GetRoomMessagesResponse response = new GetRoomMessagesResponse();
            return ResponseEntity.status(HttpStatus.LOCKED).body(response);
        }
        final Page<Message> pagedMessages = messageService.getRoomMessages(id, page, size);
        List<Message> messages = Lists.reverse(pagedMessages.getContent());

        final List<GetMessageResponse> roomResponses = messageMapper.mapAsList(messages, GetMessageResponse.class);

        UserLastSeenMessageInRoom userLastSeenMessageInRoom = new UserLastSeenMessageInRoom(user, 0L, id);
        userLastSeenMessageInRoom = user.getUserLastSeenMessageInRooms().stream().filter(lastMessage -> lastMessage.getRoomId().equals(id)).findFirst().orElse(userLastSeenMessageInRoom);

        Long newLastMessageId = userLastSeenMessageInRoom.getMessageId();
        for (Message message : pagedMessages.getContent()) {
            if (message.getId() > newLastMessageId) {
                newLastMessageId = message.getId();
            }
        }

        if (newLastMessageId > userLastSeenMessageInRoom.getMessageId()) {
            userLastSeenMessageInRoom.setMessageId(newLastMessageId);
            userService.createOrUpdateUserLastSeenMessageInRoom(userLastSeenMessageInRoom);
        }

        final GetRoomMessagesResponse response = new GetRoomMessagesResponse();
        response.setMessages(roomResponses);
        response.setPage(pagedMessages.getNumber());
        response.setSize(pagedMessages.getSize());
        response.setTotalElements(pagedMessages.getTotalElements());
        response.setTotalPages(pagedMessages.getTotalPages());
        response.setLast(pagedMessages.isLast());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetRoomNewMessagesResponse> getRoomNewMessages(final UserPrincipal principal, final Long id) {
        final User user = userService.getUserById(principal.getId());
        final Room room = roomService.getRoomById(id);
        if (!room.getUsers().contains(user)) {
            GetRoomNewMessagesResponse response = new GetRoomNewMessagesResponse();
            return ResponseEntity.status(HttpStatus.LOCKED).body(response);
        }

        UserLastSeenMessageInRoom userLastSeenMessageInRoom = new UserLastSeenMessageInRoom(user, 0L, id);
        userLastSeenMessageInRoom = user.getUserLastSeenMessageInRooms().stream().filter(lastMessage -> lastMessage.getRoomId().equals(id)).findFirst().orElse(userLastSeenMessageInRoom);

        List<Message> messages = messageService.getRoomMessagesAfterId(id, userLastSeenMessageInRoom.getMessageId());

        Long newLastMessageId = userLastSeenMessageInRoom.getMessageId();
        for (Message message : messages) {
            if (message.getId() > newLastMessageId) {
                newLastMessageId = message.getId();
            }
        }

        if (newLastMessageId > userLastSeenMessageInRoom.getMessageId()) {
            userLastSeenMessageInRoom.setMessageId(newLastMessageId);
            userService.createOrUpdateUserLastSeenMessageInRoom(userLastSeenMessageInRoom);
        }

        final List<GetMessageResponse> messageResponses = messageMapper.mapAsList(messages, GetMessageResponse.class);

        final GetRoomNewMessagesResponse response = new GetRoomNewMessagesResponse();
        response.setMessages(messageResponses);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateMessageResponse> createMessageInRoom(final UserPrincipal principal, final Long id, final CreateMessageInRoomRequest request) {
        final User user = userService.getUserById(principal.getId());
        final Room room = roomService.getRoomById(id);
        if (!room.getUsers().contains(user)) {
            CreateMessageResponse response = new CreateMessageResponse();
            response.setMessage("You have been kicked from this room.");
            return ResponseEntity.status(HttpStatus.LOCKED).body(response);
        }

        if (badWordService.textContainsBadWord(request.getMessage())) {
            roomService.removeUserFromRoom(principal.getId(), id);

            CreateMessageResponse response = new CreateMessageResponse();
            response.setMessage("Bad word found.");
            return ResponseEntity.status(HttpStatus.LOCKED).body(response);
        }

        CreateMessageDto dto = new CreateMessageDto();
        dto.setUserId(principal.getId());
        dto.setRoomId(id);
        dto.setMessage(request.getMessage());

        messageService.createMessage(dto);

        CreateMessageResponse response = new CreateMessageResponse();
        response.setMessage("Message successfully created");
        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<JoinRoomResponse> joinRoom(final UserPrincipal principal, final Long id) {
        roomService.addUserToRoom(principal.getId(), id);

        JoinRoomResponse response = new JoinRoomResponse();
        response.setMessage("Successfully joined room");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LeaveRoomResponse> leaveRoom(final UserPrincipal principal, final Long id) {
        roomService.removeUserFromRoom(principal.getId(), id);

        LeaveRoomResponse response = new LeaveRoomResponse();
        response.setMessage("Successfully left room");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<KickUserFromRoomResponse> removeUserFromRoom(final String username, final Long roomId) {
        roomService.removeUserFromRoom(username, roomId);

        KickUserFromRoomResponse response = new KickUserFromRoomResponse();
        response.setMessage("Successfully removed from room");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DeleteMessageResponse> deleteMessageById(Long messageId) {
        messageService.deleteMessageById(messageId);

        DeleteMessageResponse response = new DeleteMessageResponse();
        response.setMessage("Message successfully deleted.");
        return ResponseEntity.ok(response);
    }
}
