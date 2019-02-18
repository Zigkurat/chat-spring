package com.example.chat.persistence.repository;

import com.example.chat.persistence.entity.badword.BadWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadWordRepository extends JpaRepository<BadWord, Long> {
    boolean existsByText(String text);

    boolean existsByTextIn(List<String> texts);
}
