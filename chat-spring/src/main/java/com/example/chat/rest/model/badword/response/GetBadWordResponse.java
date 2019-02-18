package com.example.chat.rest.model.badword.response;

import java.io.Serializable;
import java.util.Objects;

public class GetBadWordResponse implements Serializable {
    private static final long serialVersionUID = -4402062791481789016L;

    private Long id;

    private String text;

    public GetBadWordResponse() {
    }

    public GetBadWordResponse(final Long id, final String text) {
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
        if (!(o instanceof GetBadWordResponse)) return false;
        GetBadWordResponse that = (GetBadWordResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "GetBadWordResponse{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
