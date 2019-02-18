package com.example.chat.persistence.entity.badword;

import com.example.chat.persistence.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "bad_word")
public class BadWord extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String text;

    public BadWord() {
    }

    public BadWord(final String text) {
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
        if (!(o instanceof BadWord)) return false;
        if (!super.equals(o)) return false;
        BadWord badWord = (BadWord) o;
        return Objects.equals(text, badWord.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }

    @Override
    public String toString() {
        return "BadWord{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }
}
