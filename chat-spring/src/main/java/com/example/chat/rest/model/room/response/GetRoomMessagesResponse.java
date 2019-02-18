package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GetRoomMessagesResponse implements Serializable {
    private static final long serialVersionUID = 22612758939950698L;

    private List<GetMessageResponse> messages;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    public GetRoomMessagesResponse() {
    }

    public GetRoomMessagesResponse(List<GetMessageResponse> messages, int page, int size, long totalElements, int totalPages, boolean last) {
        this.messages = messages;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetRoomMessagesResponse)) return false;
        GetRoomMessagesResponse that = (GetRoomMessagesResponse) o;
        return page == that.page &&
                size == that.size &&
                totalElements == that.totalElements &&
                totalPages == that.totalPages &&
                last == that.last &&
                Objects.equals(messages, that.messages);
    }

    public List<GetMessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<GetMessageResponse> messages) {
        this.messages = messages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, page, size, totalElements, totalPages, last);
    }

    @Override
    public String toString() {
        return "GetRoomMessagesResponse{" +
                "messages=" + messages +
                ", page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                '}';
    }
}
