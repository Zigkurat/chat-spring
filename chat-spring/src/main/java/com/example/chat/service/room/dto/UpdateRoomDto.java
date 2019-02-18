package com.example.chat.service.room.dto;

import java.util.Objects;

public class UpdateRoomDto {

    private Long id;

    private String name;

    public UpdateRoomDto() {
    }

    public UpdateRoomDto(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
        if (!(o instanceof UpdateRoomDto)) return false;
        UpdateRoomDto dto = (UpdateRoomDto) o;
        return Objects.equals(id, dto.id) &&
                Objects.equals(name, dto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UpdateRoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
