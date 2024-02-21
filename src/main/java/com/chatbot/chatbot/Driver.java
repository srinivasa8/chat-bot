package com.chatbot.chatbot;

import com.chatbot.chatbot.repositories.ChatHistoryRepository;
import com.chatbot.chatbot.service.ChatGPTService;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class Driver {
    public static void main(String[] args) {

        String localPath = "{LOCALPATH}" + new Date().getTime() + "\\";
        String apiKey = "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";
        String userName = "{GITHUB_USER_NAME}";
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();


        restTemplate.getInterceptors().add(
                (request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + apiKey);
                    return execution.execute(request, body);
                }
        );
        ChatHistoryRepository chatHistoryRepository = new ChatHistoryRepository();
        String sessionId="session-1";
        ChatGPTService chatGPTService = new ChatGPTService(restTemplate, apiUrl, chatHistoryRepository,sessionId);

        chatGPTService.hitChatGPT("Hi, Iam Indian");
        System.out.println("[MAP1]:"+chatHistoryRepository.getChatHistory(sessionId));
        System.out.println();
        chatGPTService.hitChatGPT("What is the capital city of my country ?");
        System.out.println("[MAP2]:"+chatHistoryRepository.getChatHistory(sessionId));
        System.out.println();
        chatGPTService.hitChatGPT("How huge is my country?");
        System.out.println("[MAP3]:"+chatHistoryRepository.getChatHistory(sessionId));
    }
}
