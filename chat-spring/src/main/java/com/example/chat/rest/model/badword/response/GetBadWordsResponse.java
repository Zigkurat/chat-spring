package com.example.chat.rest.model.badword.response;

import com.example.chat.persistence.entity.badword.BadWord;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GetBadWordsResponse implements Serializable {
    private static final long serialVersionUID = 8386976730531075876L;

    private List<GetBadWordResponse> badWords;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    public GetBadWordsResponse() {
    }

    public GetBadWordsResponse(final List<GetBadWordResponse> badWords, final int page, final int size, final long totalElements, final int totalPages, final boolean last) {
        this.badWords = badWords;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<GetBadWordResponse> getBadWords() {
        return badWords;
    }

    public void setBadWords(final List<GetBadWordResponse> badWords) {
        this.badWords = badWords;
    }

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(final boolean last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetBadWordsResponse)) return false;
        GetBadWordsResponse that = (GetBadWordsResponse) o;
        return page == that.page &&
                size == that.size &&
                totalElements == that.totalElements &&
                totalPages == that.totalPages &&
                last == that.last &&
                Objects.equals(badWords, that.badWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badWords, page, size, totalElements, totalPages, last);
    }

    @Override
    public String toString() {
        return "GetBadWordsResponse{" +
                "badWords=" + badWords +
                ", page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                '}';
    }
}
