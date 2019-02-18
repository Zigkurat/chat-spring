package com.example.chat.service.message;

import com.example.chat.persistence.entity.message.Message;
import com.example.chat.service.message.dto.CreateMessageDto;
import com.example.chat.service.message.dto.UpdateMessageDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
    Page<Message> getRoomMessages(Long roomId, int page, int size);

    List<Message> getRoomMessagesAfterId(Long roomId, Long messageId);

    Message createMessage(CreateMessageDto dto);

    Message updateMessage(UpdateMessageDto dto);

    boolean deleteMessageById(Long id);
}
