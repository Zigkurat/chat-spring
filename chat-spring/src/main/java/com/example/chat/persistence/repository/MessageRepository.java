package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.message.Message;
import com.example.chat.persistence.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRoomEqualsAndIdAfterOrderByCreatedAtAsc(Room room, Long id);
}
