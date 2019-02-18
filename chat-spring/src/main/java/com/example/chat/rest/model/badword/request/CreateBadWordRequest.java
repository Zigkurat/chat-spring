package com.example.chat.rest.model.badword.request;

import java.io.Serializable;
import java.util.Objects;

public class CreateBadWordRequest implements Serializable {
    private static final long serialVersionUID = -6123125943394239065L;

    private String text;

    public CreateBadWordRequest() {
    }

    public CreateBadWordRequest(final String text) {
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
        if (!(o instanceof CreateBadWordRequest)) return false;
        CreateBadWordRequest that = (CreateBadWordRequest) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "CreateBadWordRequest{" +
                "text='" + text + '\'' +
                '}';
    }
}
