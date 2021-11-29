package com.example.messagingrabbitmq;

import java.io.Serializable;

public class MessageData implements Serializable {
    private final long senderId;
    private final String content;

    public MessageData(long from, String message) {
        this.senderId = from;
        this.content = message;
    }

    public long getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }
}
