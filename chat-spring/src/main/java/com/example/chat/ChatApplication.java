package com.example.chat;

import com.example.chat.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class ChatApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

}

