package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GetRoomsResponse implements Serializable {
    private static final long serialVersionUID = 1719810752279469077L;

    private List<GetRoomResponse> rooms;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    public GetRoomsResponse() {
    }

    public GetRoomsResponse(final List<GetRoomResponse> rooms, final int page, final int size, final long totalElements, final int totalPages, final boolean last) {
        this.rooms = rooms;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<GetRoomResponse> getRooms() {
        return rooms;
    }

    public void setRooms(final List<GetRoomResponse> rooms) {
        this.rooms = rooms;
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
        if (!(o instanceof GetRoomsResponse)) return false;
        GetRoomsResponse that = (GetRoomsResponse) o;
        return page == that.page &&
                size == that.size &&
                totalElements == that.totalElements &&
                totalPages == that.totalPages &&
                last == that.last &&
                Objects.equals(rooms, that.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms, page, size, totalElements, totalPages, last);
    }

    @Override
    public String toString() {
        return "GetRoomsResponse{" +
                "rooms=" + rooms +
                ", page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                '}';
    }
}
