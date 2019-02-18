package com.example.chat.rest.model.room.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class CreateRoomRequest implements Serializable {
    private static final long serialVersionUID = 8956251178356253244L;

    @NotBlank
    private String name;

    public CreateRoomRequest() {
    }

    public CreateRoomRequest(@NotBlank final String name) {
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
        if (!(o instanceof CreateRoomRequest)) return false;
        CreateRoomRequest that = (CreateRoomRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CreateRoomRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
