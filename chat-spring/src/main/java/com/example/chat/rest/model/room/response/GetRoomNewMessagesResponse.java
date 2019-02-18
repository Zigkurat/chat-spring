package com.example.chat.rest.model.room.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GetRoomNewMessagesResponse implements Serializable {
    private static final long serialVersionUID = -4036283776561920657L;

    private List<GetMessageResponse> messages;

    public GetRoomNewMessagesResponse() {
    }

    public GetRoomNewMessagesResponse(List<GetMessageResponse> messages) {
        this.messages = messages;
    }

    public List<GetMessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<GetMessageResponse> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetRoomNewMessagesResponse)) return false;
        GetRoomNewMessagesResponse response = (GetRoomNewMessagesResponse) o;
        return Objects.equals(messages, response.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }

    @Override
    public String toString() {
        return "GetRoomNewMessagesResponse{" +
                "messages=" + messages +
                '}';
    }
}
