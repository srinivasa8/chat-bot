package com.chatbot.chatbot.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.chatbot.chatbot.model.ChatGPTRequest;
import com.chatbot.chatbot.model.ChatGPTResponse;
import com.chatbot.chatbot.model.Message;
import com.chatbot.chatbot.repositories.ChatHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ChatGPTService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${chatgpt.api.url}")
    private String apiUrl;


    private ChatHistoryRepository chatHistoryRepository;
    private String sessionId;

    public ChatGPTService(RestTemplate restTemplate, String apiUrl, ChatHistoryRepository chatHistoryRepository
    , String sessionId) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.chatHistoryRepository=chatHistoryRepository;
        this.sessionId=sessionId;
    }
    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChatGPTService(){

    }

    public ChatGPTResponse hitChatGPT(String prompt){
        //ChatHistoryRepository chatHistoryRepository = new ChatHistoryRepository();
        List<Message> messages = chatHistoryRepository.getChatHistory(sessionId);
        messages.add(new Message("user", prompt));
        ChatGPTRequest req = new ChatGPTRequest("gpt-3.5-turbo", messages);
        System.out.println("[ChatGPTRequest Input]:"+req.toString());
        ChatGPTResponse response = restTemplate.postForObject(apiUrl, req, ChatGPTResponse.class);
        //String response = restTemplate.postForObject(apiUrl, req, String.class);
        System.out.println("[ChatGPTResponse Output]:"+response.toString());
        Message chatGptResponseMessage = response.getChoices().get(0).getMessage();
        chatHistoryRepository.updateChatHistory(sessionId,prompt,true);
        chatHistoryRepository.updateChatHistory(sessionId,chatGptResponseMessage.getContent(),false);
        System.out.println("response message==>"+chatGptResponseMessage.getContent());
        return response;
    }

}
