package com.example.chat.service.badword.dto;

import java.util.Objects;

public class CreateBadWordDto {
    private String text;

    public CreateBadWordDto() {
    }

    public CreateBadWordDto(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateBadWordDto)) return false;
        CreateBadWordDto that = (CreateBadWordDto) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "CreateBadWordDto{" +
                "text='" + text + '\'' +
                '}';
    }
}
