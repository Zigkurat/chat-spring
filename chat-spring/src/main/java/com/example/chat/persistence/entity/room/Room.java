package com.example.chat.persistence.entity.room;

import com.example.chat.persistence.entity.base.BaseEntity;
import com.example.chat.persistence.entity.message.Message;
import com.example.chat.persistence.entity.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "room_user", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private Set<Message> messages = new HashSet<>();

    public Room() {
    }

    public Room(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(final Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        if (!super.equals(o)) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) &&
                Objects.equals(users, room.users) &&
                Objects.equals(messages, room.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", messages=" + messages +
                "} " + super.toString();
    }
}
