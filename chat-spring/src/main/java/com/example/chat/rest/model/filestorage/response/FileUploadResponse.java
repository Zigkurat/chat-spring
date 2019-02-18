package com.example.chat.rest.model.filestorage.response;

import java.io.Serializable;
import java.util.Objects;

public class FileUploadResponse implements Serializable {
    private static final long serialVersionUID = -3581185301046336428L;

    private String imageUrl;

    public FileUploadResponse() {
    }

    public FileUploadResponse(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileUploadResponse)) return false;
        FileUploadResponse response = (FileUploadResponse) o;
        return Objects.equals(imageUrl, response.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl);
    }

    @Override
    public String toString() {
        return "FileUploadResponse{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
