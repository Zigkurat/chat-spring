package com.example.chat.service.message.dto;

import java.util.Objects;

public class CreateMessageDto {
    private Long userId;

    private Long roomId;

    private String message;

    public CreateMessageDto() {
    }

    public CreateMessageDto(final Long userId, final Long roomId, final String message) {
        this.userId = userId;
        this.roomId = roomId;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(final Long roomId) {
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateMessageDto)) return false;
        CreateMessageDto that = (CreateMessageDto) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roomId, message);
    }

    @Override
    public String toString() {
        return "CreateMessageDto{" +
                "userId=" + userId +
                ", roomId=" + roomId +
                ", message='" + message + '\'' +
                '}';
    }
}
