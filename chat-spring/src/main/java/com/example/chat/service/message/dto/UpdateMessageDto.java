package com.example.chat.service.message.dto;

import java.util.Objects;

public class UpdateMessageDto {
    private Long id;

    private String message;

    public UpdateMessageDto() {
    }

    public UpdateMessageDto(final Long messageId, final String message) {
        this.id = messageId;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
        if (!(o instanceof UpdateMessageDto)) return false;
        UpdateMessageDto that = (UpdateMessageDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }

    @Override
    public String toString() {
        return "UpdateMessageDto{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
