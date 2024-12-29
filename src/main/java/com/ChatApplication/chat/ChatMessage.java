package com.ChatApplication.chat;

public class ChatMessage {
    private String sender;
    private String content;
    private String type;

    // Constructor, getters, and setters here...


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Builder pattern implementation
    public static class Builder {
        private String sender;
        private String content;
        private String type;

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public ChatMessage build() {
            ChatMessage message = new ChatMessage();
            message.sender = this.sender;
            message.content = this.content;
            message.type = this.type;
            return message;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
