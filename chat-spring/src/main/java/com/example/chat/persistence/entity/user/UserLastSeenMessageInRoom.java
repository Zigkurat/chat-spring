package com.example.chat.persistence.entity.user;

import com.example.chat.persistence.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_last_seen_message_in_room")
public class UserLastSeenMessageInRoom extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    private Long messageId;

    private Long roomId;

    public UserLastSeenMessageInRoom() {
    }

    public UserLastSeenMessageInRoom(User owner, Long messageId, Long roomId) {
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
        if (!(o instanceof UserLastSeenMessageInRoom)) return false;
        if (!super.equals(o)) return false;
        UserLastSeenMessageInRoom that = (UserLastSeenMessageInRoom) o;
        return Objects.equals(messageId, that.messageId) &&
                Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), messageId, roomId);
    }

    @Override
    public String toString() {
        return "UserLastSeenMessageInRoom{" +
                "messageId=" + messageId +
                ", roomId=" + roomId +
                "} " + super.toString();
    }
}
