package com.example.chat.rest.controller;

import com.example.chat.rest.facade.filestorage.FileStorageFacade;
import com.example.chat.rest.model.filestorage.response.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/storage")
public class FileStorageController {

    private final FileStorageFacade fileStorageFacade;

    public FileStorageController(final FileStorageFacade fileStorageFacade) {
        this.fileStorageFacade = fileStorageFacade;
    }

    @PostMapping("/")
    public ResponseEntity<FileUploadResponse> uploadImage(@RequestParam("image") final MultipartFile image) {
        return fileStorageFacade.uploadImage(image);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable final String fileName, final HttpServletRequest request) {
        return fileStorageFacade.downloadImage(fileName, request);
    }
}
