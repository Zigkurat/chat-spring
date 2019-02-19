package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    Optional<User> findByUsername(String username);
}
