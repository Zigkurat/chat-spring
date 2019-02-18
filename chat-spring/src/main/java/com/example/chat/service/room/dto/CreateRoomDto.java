package com.example.chat.service.room.dto;

import java.util.Objects;

public class CreateRoomDto {

    private String name;

    public CreateRoomDto() {
    }

    public CreateRoomDto(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateRoomDto)) return false;
        CreateRoomDto that = (CreateRoomDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CreateRoomDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
