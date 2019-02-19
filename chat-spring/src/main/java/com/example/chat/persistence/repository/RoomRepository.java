package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.room.Room;
import com.example.chat.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByName(String name);

    List<Room> findAllByUsersContaining(User user);
}
