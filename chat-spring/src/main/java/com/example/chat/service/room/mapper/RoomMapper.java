package com.example.chat.service.room.mapper;

import com.example.chat.persistence.entity.room.Room;
import com.example.chat.rest.model.room.request.CreateRoomRequest;
import com.example.chat.rest.model.room.request.UpdateRoomRequest;
import com.example.chat.rest.model.room.response.GetRoomResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper extends ConfigurableMapper {

    @Override
    protected void configure(final MapperFactory factory) {
        factory.classMap(Room.class, CreateRoomRequest.class)
                .byDefault()
                .register();

        factory.classMap(Room.class, UpdateRoomRequest.class)
                .byDefault()
                .register();

        factory.classMap(GetRoomResponse.class, Room.class)
                .byDefault()
                .register();
    }
}
