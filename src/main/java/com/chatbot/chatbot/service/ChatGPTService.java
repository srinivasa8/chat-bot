package com.chatbot.chatbot.service;

import com.chatbot.chatbot.model.ChatGPTRequest;
import com.chatbot.chatbot.model.ChatGPTResponse;
import com.chatbot.chatbot.model.Message;
import com.chatbot.chatbot.repositories.ChatHistoryRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ChatGPTService {

    private RestTemplate restTemplate;

    private String apiUrl;

    private ChatHistoryRepository chatHistoryRepository;

    private String sessionId;

    public ChatGPTService(RestTemplate restTemplate, String apiUrl, ChatHistoryRepository chatHistoryRepository) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.chatHistoryRepository = chatHistoryRepository;
    }

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChatGPTService() {

    }

    private int availableToken = 3;

    public ChatGPTResponse hitChatGPT(String prompt) {
        ChatGPTResponse chatGptResponse = new ChatGPTResponse();
        try {
            if (sessionId == null) {
                sessionId = "session-1";
            }
            //To avoid token exceed error from chatGPT
            if (availableToken == 0) {
                System.out.println("Seems you have exceeded your requests per min(RPM) limit, Please wait " +
                        "for few seconds, your request will be automatically sent to chatGPT!");
                Thread.sleep(30000);
                availableToken = 3;
            }
            System.out.println();

            List<Message> messages = chatHistoryRepository.getChatHistory(sessionId);
            messages.add(new Message("user", prompt));

            ChatGPTRequest chatGPTRequest = new ChatGPTRequest("gpt-3.5-turbo", messages);
            System.out.println("User : " + prompt);

            chatGptResponse = restTemplate.postForObject(apiUrl, chatGPTRequest, ChatGPTResponse.class);

            Message chatGptResponseMessage = chatGptResponse.getChoices().get(0).getMessage();
            System.out.println("ChatGpt : " + chatGptResponseMessage.getContent());

            chatHistoryRepository.updateChatHistory(sessionId, prompt, true);
            chatHistoryRepository.updateChatHistory(sessionId, chatGptResponseMessage.getContent(), false);

            availableToken--;
        } catch (Exception e) {
            System.out.println("Exception occurred while calling ChatGPT API with error:" + e);
        }
        return chatGptResponse;
    }

}
