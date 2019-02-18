package com.example.chat.service.badword.impl;

import com.example.chat.persistence.entity.badword.BadWord;
import com.example.chat.persistence.repository.BadWordRepository;
import com.example.chat.service.badword.BadWordService;
import com.example.chat.service.badword.dto.CreateBadWordDto;
import com.example.chat.service.badword.dto.UpdateBadWordDto;
import com.example.chat.service.badword.exception.BadWordAlreadyExistsException;
import com.example.chat.service.badword.exception.BadWordNotFoundForIdException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class BadWordServiceImpl implements BadWordService {

    private BadWordRepository badWordRepository;

    public BadWordServiceImpl(final BadWordRepository badWordRepository) {
        this.badWordRepository = badWordRepository;
    }

    @Override
    public Page<BadWord> getBadWords(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "text");
        return badWordRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public BadWord createBadWord(final CreateBadWordDto dto) {
        if (badWordRepository.existsByText(dto.getText())) {
            throw new BadWordAlreadyExistsException(dto.getText());
        }

        final BadWord badWord = new BadWord(dto.getText());

        return badWordRepository.save(badWord);
    }

    @Override
    public BadWord getBadWordById(Long id) {
        return badWordRepository.findById(id)
                .orElseThrow(() -> new BadWordNotFoundForIdException(id));
    }

    @Override
    @Transactional
    public BadWord updateBadWord(final UpdateBadWordDto dto) {
        if (badWordRepository.existsByText(dto.getText())) {
            throw new BadWordAlreadyExistsException(dto.getText());
        }
        final BadWord badWord = badWordRepository.findById(dto.getId())
                .orElseThrow(() -> new BadWordNotFoundForIdException(dto.getId()));

        badWord.setText(dto.getText());

        return badWordRepository.save(badWord);
    }

    @Override
    @Transactional
    public boolean deleteBadWordById(final Long id) {
        final BadWord badWord = badWordRepository.findById(id)
                .orElseThrow(() -> new BadWordNotFoundForIdException(id));

        badWordRepository.delete(badWord);

        return true;
    }

    @Override
    public boolean textContainsBadWord(final String text) {
        final List<String> words = Arrays.asList(text.split(" "));

        return badWordRepository.existsByTextIn(words);
    }
}
