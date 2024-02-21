package com.chatbot.chatbot.model;

import java.util.List;

public class ChatGPTResponse {

    private String id;
    private String model;
    private Long createdDate;
    private List<Choice> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "ChatGPTResponse{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", createdDate=" + createdDate +
                ", choices=" + choices.toString() +
                '}';
    }

}
