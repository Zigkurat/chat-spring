package com.example.chat.service.badword.mapper;

import com.example.chat.rest.model.badword.request.CreateBadWordRequest;
import com.example.chat.rest.model.badword.request.UpdateBadWordRequest;
import com.example.chat.service.badword.dto.CreateBadWordDto;
import com.example.chat.service.badword.dto.UpdateBadWordDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class BadWordMapper extends ConfigurableMapper {
    @Override
    protected void configure(final MapperFactory factory) {
        factory.classMap(CreateBadWordDto.class, CreateBadWordRequest.class)
                .byDefault()
                .register();

        factory.classMap(UpdateBadWordDto.class, UpdateBadWordRequest.class)
                .byDefault()
                .register();
    }
}
