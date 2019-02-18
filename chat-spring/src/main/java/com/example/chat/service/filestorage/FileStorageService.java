package com.example.chat.service.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String uploadFile(MultipartFile file);

    Resource downloadFile(String filename);
}
