package com.example.chat.rest.controller;

import com.example.chat.rest.constant.RestConstants;
import com.example.chat.rest.facade.badword.BadWordFacade;
import com.example.chat.rest.model.badword.request.CreateBadWordRequest;
import com.example.chat.rest.model.badword.request.UpdateBadWordRequest;
import com.example.chat.rest.model.badword.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/badword")
public class BadWordController {

    private final BadWordFacade badWordFacade;

    public BadWordController(final BadWordFacade badWordFacade) {
        this.badWordFacade = badWordFacade;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<GetBadWordsResponse> getBadWords(@RequestParam(value = "page", defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) final int page,
                                                    @RequestParam(value = "size", defaultValue = RestConstants.DEFAULT_PAGE_SIZE) final int size) {
        return badWordFacade.getBadWords(page, size);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<CreateBadWordResponse> createBadWord(@Valid @RequestBody final CreateBadWordRequest request) {
        return badWordFacade.createBadWord(request);
    }

    @GetMapping("/{badWordId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<GetBadWordResponse> getBadWordById(@PathVariable("badWordId") final Long id) {
        return badWordFacade.getBadWordById(id);
    }

    @PutMapping("/{badWordId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UpdateBadWordResponse> updateBadWordById(@PathVariable("badWordId") final Long id, @Valid @RequestBody final UpdateBadWordRequest request) {
        return badWordFacade.updateBadWordById(id, request);
    }

    @DeleteMapping("/{badWordId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<DeleteBadWordResponse> deleteBadWordById(@PathVariable("badWordId") final Long id) {
        return badWordFacade.deleteBadWordById(id);
    }
}
