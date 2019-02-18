package com.example.chat.service.user.mapper;

import com.example.chat.persistence.entity.user.User;
import com.example.chat.rest.model.user.request.CreateUserRequest;
import com.example.chat.rest.model.user.request.UpdateCurrentUserRequest;
import com.example.chat.rest.model.user.request.UpdateUserByIdRequest;
import com.example.chat.rest.model.user.response.GetUserResponse;
import com.example.chat.service.user.dto.CreateUserDto;
import com.example.chat.service.user.dto.UpdateUserDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ConfigurableMapper {

    @Override
    protected void configure(final MapperFactory factory) {
        factory.classMap(CreateUserDto.class, CreateUserRequest.class)
                .byDefault()
                .register();

        factory.classMap(UpdateUserDto.class, UpdateCurrentUserRequest.class)
                .byDefault()
                .register();

        factory.classMap(UpdateUserDto.class, UpdateUserByIdRequest.class)
                .byDefault()
                .register();

        factory.classMap(GetUserResponse.class, User.class)
                .byDefault()
                .register();
    }
}
