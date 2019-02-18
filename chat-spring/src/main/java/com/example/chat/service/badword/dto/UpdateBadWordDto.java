package com.example.chat.service.badword.dto;

import java.util.Objects;

public class UpdateBadWordDto {
    private Long id;
    private String text;

    public UpdateBadWordDto() {
    }

    public UpdateBadWordDto(final Long id, final String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
        if (!(o instanceof UpdateBadWordDto)) return false;
        UpdateBadWordDto that = (UpdateBadWordDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "UpdateBadWordDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
