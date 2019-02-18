package com.example.chat.rest.model.room.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class UpdateRoomRequest implements Serializable {
    private static final long serialVersionUID = 1745767019979934549L;

    @NotBlank
    private String name;

    public UpdateRoomRequest() {
    }

    public UpdateRoomRequest(final String name) {
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
        if (!(o instanceof UpdateRoomRequest)) return false;
        UpdateRoomRequest that = (UpdateRoomRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UpdateRoomRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
