package com.example.chat.persistence.entity.message;

import com.example.chat.persistence.entity.base.BaseEntity;
import com.example.chat.persistence.entity.room.Room;
import com.example.chat.persistence.entity.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private String message;

    public Message() {
    }

    public Message(final User user, final Room room, final String message) {
        this.user = user;
        this.room = room;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(final Room room) {
        this.room = room;
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
        if (!(o instanceof Message)) return false;
        if (!super.equals(o)) return false;
        Message message1 = (Message) o;
        return Objects.equals(user, message1.user) &&
                Objects.equals(room, message1.room) &&
                Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, message);
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", room=" + room +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }
}
