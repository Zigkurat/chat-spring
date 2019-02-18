package com.example.chat.rest.facade.filestorage.impl;

import com.example.chat.rest.facade.filestorage.FileStorageFacade;
import com.example.chat.rest.model.filestorage.response.FileUploadResponse;
import com.example.chat.service.filestorage.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class FileStorageFacadeImpl implements FileStorageFacade {

    private final FileStorageService fileStorageService;

    public FileStorageFacadeImpl(final FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ResponseEntity<FileUploadResponse> uploadImage(final MultipartFile image) {
        final String filename = fileStorageService.uploadFile(image);

        final FileUploadResponse response = new FileUploadResponse();
        response.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/storage/")
                .path(filename)
                .toUriString());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Resource> downloadImage(final String filename, final HttpServletRequest request) {
        final Resource resource = fileStorageService.downloadFile(filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
