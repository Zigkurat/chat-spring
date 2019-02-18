package com.example.chat.service.badword;

import com.example.chat.persistence.entity.badword.BadWord;
import com.example.chat.service.badword.dto.CreateBadWordDto;
import com.example.chat.service.badword.dto.UpdateBadWordDto;
import org.springframework.data.domain.Page;

public interface BadWordService {
    Page<BadWord> getBadWords(int page, int size);

    BadWord createBadWord(CreateBadWordDto dto);

    BadWord getBadWordById(Long id);

    BadWord updateBadWord(UpdateBadWordDto dto);

    boolean deleteBadWordById(Long id);

    boolean textContainsBadWord(String text);
}
