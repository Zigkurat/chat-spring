package com.example.chat.rest.model.user.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GetUsersResponse implements Serializable {
    private static final long serialVersionUID = 3424614073752981451L;

    private List<GetUserResponse> users;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    public GetUsersResponse() {
    }

    public List<GetUserResponse> getUsers() {
        return users;
    }

    public void setUsers(final List<GetUserResponse> users) {
        this.users = users;
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
        if (!(o instanceof GetUsersResponse)) return false;
        GetUsersResponse response = (GetUsersResponse) o;
        return page == response.page &&
                size == response.size &&
                totalElements == response.totalElements &&
                totalPages == response.totalPages &&
                last == response.last &&
                Objects.equals(users, response.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, page, size, totalElements, totalPages, last);
    }

    @Override
    public String toString() {
        return "GetUsersResponse{" +
                "users=" + users +
                ", page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                '}';
    }
}
