package com.example.chat.service.filestorage.impl;

import com.example.chat.service.filestorage.FileStorageService;
import com.example.chat.service.filestorage.exception.FileNotFoundException;
import com.example.chat.service.filestorage.exception.UploadFailedException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Override
    public String uploadFile(final MultipartFile file) {
        final String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        final String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        try {
            final Path targetLocation = Paths.get("./storage").toAbsolutePath().normalize().resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new UploadFailedException(originalFileName);
        }
    }

    @Override
    public Resource downloadFile(final String filename) {
        try {
            final Path filePath = Paths.get("./storage").toAbsolutePath().normalize().resolve(filename).normalize();
            final Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException(filename);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException(filename);
        }
    }
}
