package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.room.Room;
import com.example.chat.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByName(String name);

    List<Room> findAllByUsersContaining(User user);
}
