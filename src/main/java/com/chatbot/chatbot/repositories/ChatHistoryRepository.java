package com.chatbot.chatbot.repositories;

import com.chatbot.chatbot.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatHistoryRepository {

    private HashMap<String, List<Message>> chatHistoryMap;

    public ChatHistoryRepository() {
        this.chatHistoryMap = new HashMap<>();
    }

    public void updateChatHistory(String sessionId, String message, boolean isUser) {
        List<Message> chat = chatHistoryMap.getOrDefault(sessionId, new ArrayList<>());
        if (isUser) {
            //chat.append("User: ");
            chat.add(new Message("user", message));
        } else {
            chat.add(new Message("system", message));
           // chat.append("ChatGPT: ");
        }
       chatHistoryMap.put(sessionId,chat);
    }

    public List<Message> getChatHistory(String sessionId){
        return chatHistoryMap.getOrDefault(sessionId, new ArrayList<>());
    }
}
