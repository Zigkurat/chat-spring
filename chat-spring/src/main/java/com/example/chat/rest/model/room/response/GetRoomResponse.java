package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.Objects;

public class GetRoomResponse implements Serializable {
    private static final long serialVersionUID = -2754724782911392183L;

    private Long id;

    private String name;

    private boolean isJoined;

    public GetRoomResponse() {
    }

    public GetRoomResponse(Long id, String name, boolean isJoined) {
        this.id = id;
        this.name = name;
        this.isJoined = isJoined;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetRoomResponse)) return false;
        GetRoomResponse that = (GetRoomResponse) o;
        return isJoined == that.isJoined &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isJoined);
    }

    @Override
    public String toString() {
        return "GetRoomResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isJoined=" + isJoined +
                '}';
    }
}
