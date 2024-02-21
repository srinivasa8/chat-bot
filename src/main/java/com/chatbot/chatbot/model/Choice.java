package com.chatbot.chatbot.model;

public class Choice {

    private Message message;
    private Integer index;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "Message='" + message.toString() + '\'' +
                ", index=" + index +
                '}';
    }
}
