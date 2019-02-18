package com.example.chat.rest.controller;

import com.example.chat.rest.constant.RestConstants;
import com.example.chat.rest.facade.room.RoomFacade;
import com.example.chat.rest.model.room.request.CreateMessageInRoomRequest;
import com.example.chat.rest.model.room.request.CreateRoomRequest;
import com.example.chat.rest.model.room.request.UpdateRoomRequest;
import com.example.chat.rest.model.room.response.*;
import com.example.chat.security.annotation.CurrentUser;
import com.example.chat.security.model.local.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomFacade roomFacade;

    public RoomController(final RoomFacade roomFacade) {
        this.roomFacade = roomFacade;
    }

    @GetMapping("/")
    public ResponseEntity<GetRoomsResponse> getRooms(@CurrentUser final UserPrincipal principal,
                                                     @RequestParam(value = "page", defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) final int page,
                                                     @RequestParam(value = "size", defaultValue = RestConstants.DEFAULT_PAGE_SIZE) final int size) {
        return roomFacade.getRooms(principal, page, size);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateRoomResponse> createRoom(@Valid @RequestBody final CreateRoomRequest request) {
        return roomFacade.createRoom(request);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<GetRoomResponse> getRoomById(@PathVariable("roomId") final Long id) {
        return roomFacade.getRoomById(id);
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateRoomResponse> updateRoomById(@PathVariable("roomId") final Long id, @Valid @RequestBody final UpdateRoomRequest request) {
        return roomFacade.updateRoomById(id, request);
    }

    @DeleteMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeleteRoomResponse> deleteRoomById(@PathVariable("roomId") final Long id) {
        return roomFacade.deleteRoom(id);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<GetRoomMessagesResponse> getRoomMessages(@CurrentUser final UserPrincipal principal,
                                                                   @PathVariable("roomId") final Long id,
                                                                   @RequestParam(value = "page", defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) final int page,
                                                                   @RequestParam(value = "size", defaultValue = RestConstants.DEFAULT_PAGE_SIZE) final int size) {
        return roomFacade.getRoomMessages(principal, id, page, size);
    }

    @GetMapping("/{roomId}/newMessages")
    public ResponseEntity<GetRoomNewMessagesResponse> getRoomNewMessages(@CurrentUser final UserPrincipal principal, @PathVariable("roomId") final Long id) {
        return roomFacade.getRoomNewMessages(principal, id);
    }

    @PostMapping("/{roomId}/messages")
    public ResponseEntity<CreateMessageResponse> createMessageInRoom(@CurrentUser final UserPrincipal principal, @PathVariable("roomId") final Long id, @RequestBody CreateMessageInRoomRequest request) {
        return roomFacade.createMessageInRoom(principal, id, request);
    }

    @PostMapping("/{roomId}/join")
    public ResponseEntity<JoinRoomResponse> joinRoom(@CurrentUser final UserPrincipal principal, @PathVariable("roomId") final Long id) {
        return roomFacade.joinRoom(principal, id);
    }


    @PostMapping("/{roomId}/leave")
    public ResponseEntity<LeaveRoomResponse> leaveRoom(@CurrentUser final UserPrincipal principal, @PathVariable("roomId") final Long id) {
        return roomFacade.leaveRoom(principal, id);
    }

    @PostMapping("/{roomId}/kick/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KickUserFromRoomResponse> kickUserFromRoom(@PathVariable("roomId") final Long roomId, @PathVariable("username") final String username) {
        return roomFacade.removeUserFromRoom(username, roomId);
    }

    @DeleteMapping("/messages/{messageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeleteMessageResponse> deleteMessageById(@PathVariable("messageId") final Long messageId) {
        return roomFacade.deleteMessageById(messageId);
    }
}
