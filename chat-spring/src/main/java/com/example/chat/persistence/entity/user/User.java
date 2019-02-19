package com.example.chat.persistence.entity.user;

import com.example.chat.persistence.entity.base.BaseEntity;
import com.example.chat.persistence.entity.constant.AuthProvider;
import com.example.chat.persistence.entity.constant.UserRole;
import com.example.chat.persistence.entity.room.Room;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String avatarUrl;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Enumerated(value = EnumType.STRING)
    private AuthProvider provider;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "users")
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<UserLastSeenMessageInRoom> userLastSeenMessageInRooms = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String avatarUrl, String password, UserRole role, AuthProvider provider, Set<Room> rooms) {
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.rooms = rooms;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<UserLastSeenMessageInRoom> getUserLastSeenMessageInRooms() {
        return userLastSeenMessageInRooms;
    }

    public void setUserLastSeenMessageInRooms(Set<UserLastSeenMessageInRoom> userLastSeenMessageInRooms) {
        this.userLastSeenMessageInRooms = userLastSeenMessageInRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(avatarUrl, user.avatarUrl) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                provider == user.provider &&
                Objects.equals(rooms, user.rooms) &&
                Objects.equals(userLastSeenMessageInRooms, user.userLastSeenMessageInRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, avatarUrl, password, role, provider, userLastSeenMessageInRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", provider=" + provider +
                ", rooms=" + rooms +
                ", userLastSeenMessageInRooms=" + userLastSeenMessageInRooms +
                "} " + super.toString();
    }
}
