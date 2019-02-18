package com.example.chat.rest.facade.badword.impl;

import com.example.chat.persistence.entity.badword.BadWord;
import com.example.chat.rest.facade.badword.BadWordFacade;
import com.example.chat.rest.model.badword.request.CreateBadWordRequest;
import com.example.chat.rest.model.badword.request.UpdateBadWordRequest;
import com.example.chat.rest.model.badword.response.*;
import com.example.chat.service.badword.BadWordService;
import com.example.chat.service.badword.dto.CreateBadWordDto;
import com.example.chat.service.badword.dto.UpdateBadWordDto;
import com.example.chat.service.badword.mapper.BadWordMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BadWordFacadeImpl implements BadWordFacade {

    private final BadWordService badWordService;
    private final BadWordMapper badWordMapper;

    public BadWordFacadeImpl(final BadWordService badWordService, final BadWordMapper badWordMapper) {
        this.badWordService = badWordService;
        this.badWordMapper = badWordMapper;
    }

    @Override
    public ResponseEntity<GetBadWordsResponse> getBadWords(final int page, final int size) {
        final Page<BadWord> pagedBadWords = badWordService.getBadWords(page, size);
        final List<GetBadWordResponse> badWordResponses = badWordMapper.mapAsList(pagedBadWords.getContent(), GetBadWordResponse.class);

        final GetBadWordsResponse response = new GetBadWordsResponse();
        response.setBadWords(badWordResponses);
        response.setPage(pagedBadWords.getNumber());
        response.setSize(pagedBadWords.getSize());
        response.setTotalElements(pagedBadWords.getTotalElements());
        response.setTotalPages(pagedBadWords.getTotalPages());
        response.setLast(pagedBadWords.isLast());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateBadWordResponse> createBadWord(final CreateBadWordRequest request) {
        final CreateBadWordDto dto = badWordMapper.map(request, CreateBadWordDto.class);
        badWordService.createBadWord(dto);

        final CreateBadWordResponse response = new CreateBadWordResponse("Created successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetBadWordResponse> getBadWordById(final Long id) {
        final BadWord badWord = badWordService.getBadWordById(id);

        final GetBadWordResponse response = badWordMapper.map(badWord, GetBadWordResponse.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UpdateBadWordResponse> updateBadWordById(final Long id, final UpdateBadWordRequest request) {
        final UpdateBadWordDto dto = badWordMapper.map(request, UpdateBadWordDto.class);
        dto.setId(id);
        badWordService.updateBadWord(dto);

        final UpdateBadWordResponse response = new UpdateBadWordResponse("Updated successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DeleteBadWordResponse> deleteBadWordById(final Long id) {
        badWordService.deleteBadWordById(id);

        final DeleteBadWordResponse response = new DeleteBadWordResponse("Deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
