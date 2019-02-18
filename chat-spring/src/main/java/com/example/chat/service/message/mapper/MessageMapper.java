package com.example.chat.service.message.mapper;

import com.example.chat.persistence.entity.message.Message;
import com.example.chat.rest.model.room.response.GetMessageResponse;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(GetMessageResponse.class, Message.class)
                .customize(new CustomMapper<GetMessageResponse, Message>() {
                    @Override
                    public void mapBtoA(Message message, GetMessageResponse getMessageResponse, MappingContext context) {
                        super.mapBtoA(message, getMessageResponse, context);
                        getMessageResponse.setUsername(message.getUser().getUsername());
                    }
                })
                .byDefault()
                .register();
    }
}
