package com.example.chat.persistence.entity.user;

import com.example.chat.persistence.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room_last_message")
public class RoomLastMessage extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    private Long messageId;

    private Long roomId;

    public RoomLastMessage() {
    }

    public RoomLastMessage(User owner, Long messageId, Long roomId) {
        this.owner = owner;
        this.messageId = messageId;
        this.roomId = roomId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomLastMessage)) return false;
        if (!super.equals(o)) return false;
        RoomLastMessage that = (RoomLastMessage) o;
        return Objects.equals(messageId, that.messageId) &&
                Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), messageId, roomId);
    }

    @Override
    public String toString() {
        return "RoomLastMessage{" +
                "messageId=" + messageId +
                ", roomId=" + roomId +
                "} " + super.toString();
    }
}
