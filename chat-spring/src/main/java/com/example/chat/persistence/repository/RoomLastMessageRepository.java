package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.user.RoomLastMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomLastMessageRepository extends JpaRepository<RoomLastMessage, Long> {

}
