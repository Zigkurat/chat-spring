package com.example.chat.rest.facade.filestorage;

import com.example.chat.rest.model.filestorage.response.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileStorageFacade {
    ResponseEntity<FileUploadResponse> uploadImage(MultipartFile image);

    ResponseEntity<Resource> downloadImage(String path, HttpServletRequest request);
}
