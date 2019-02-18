package com.example.chat.service.message.impl;

import com.example.chat.persistence.entity.message.Message;
import com.example.chat.persistence.entity.room.Room;
import com.example.chat.persistence.entity.user.User;
import com.example.chat.persistence.repository.MessageRepository;
import com.example.chat.persistence.repository.RoomRepository;
import com.example.chat.persistence.repository.UserRepository;
import com.example.chat.service.message.MessageService;
import com.example.chat.service.message.dto.CreateMessageDto;
import com.example.chat.service.message.dto.UpdateMessageDto;
import com.example.chat.service.message.exception.MessageNotFoundForIdException;
import com.example.chat.service.room.exception.RoomNotFoundForIdException;
import com.example.chat.service.user.exception.UserNotFoundForIdException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public MessageServiceImpl(final MessageRepository messageRepository, final UserRepository userRepository, final RoomRepository roomRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<Message> getRoomMessages(final Long roomId, final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        return messageRepository.findAll(pageable);
    }

    @Override
    public List<Message> getRoomMessagesAfterId(Long roomId, Long messageId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundForIdException(roomId));

        return messageRepository.findByRoomEqualsAndIdAfterOrderByCreatedAtAsc(room, messageId);
    }

    @Override
    @Transactional
    public Message createMessage(final CreateMessageDto dto) {
        final User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundForIdException(dto.getUserId()));
        final Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RoomNotFoundForIdException(dto.getRoomId()));

        final Message message = new Message(user, room, dto.getMessage());

        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public Message updateMessage(final UpdateMessageDto dto) {
        final Message message = messageRepository.findById(dto.getId())
                .orElseThrow(() -> new MessageNotFoundForIdException(dto.getId()));

        message.setMessage(dto.getMessage());

        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public boolean deleteMessageById(final Long id) {
        final Message message = messageRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundForIdException(id));

        messageRepository.delete(message);

        return true;
    }
}
