package com.chatbot.chatbot.model;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTRequest {

    private List<Message> messages;
    private String model;

    public ChatGPTRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
        //this.messages.add(new Message("user", prompt));
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "ChatGPTRequest{" +
                "messages=" + messages +
                ", model='" + model + '\'' +
                '}';
    }
}
