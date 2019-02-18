package com.example.chat.rest.model.badword.request;

import java.io.Serializable;
import java.util.Objects;

public class UpdateBadWordRequest implements Serializable {
    private static final long serialVersionUID = 4284554355007635485L;

    private String text;

    public UpdateBadWordRequest() {
    }

    public UpdateBadWordRequest(final String text) {
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
        if (!(o instanceof UpdateBadWordRequest)) return false;
        UpdateBadWordRequest that = (UpdateBadWordRequest) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "UpdateBadWordRequest{" +
                "text='" + text + '\'' +
                '}';
    }
}
