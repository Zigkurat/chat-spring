package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.user.UserLastSeenMessageInRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLastSeenMessageInRoomRepository extends JpaRepository<UserLastSeenMessageInRoom, Long> {

}
