package com.example.chat.rest.facade.badword;

import com.example.chat.rest.model.badword.request.CreateBadWordRequest;
import com.example.chat.rest.model.badword.request.UpdateBadWordRequest;
import com.example.chat.rest.model.badword.response.*;
import org.springframework.http.ResponseEntity;

public interface BadWordFacade {

    ResponseEntity<GetBadWordsResponse> getBadWords(int page, int size);

    ResponseEntity<CreateBadWordResponse> createBadWord(CreateBadWordRequest request);

    ResponseEntity<GetBadWordResponse> getBadWordById(Long id);

    ResponseEntity<UpdateBadWordResponse> updateBadWordById(Long id, UpdateBadWordRequest request);

    ResponseEntity<DeleteBadWordResponse> deleteBadWordById(Long id);
}
