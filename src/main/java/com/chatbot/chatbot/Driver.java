package com.chatbot.chatbot;

import com.chatbot.chatbot.repositories.ChatHistoryRepository;
import com.chatbot.chatbot.service.ChatGPTService;
import org.springframework.web.client.RestTemplate;

public class Driver {

    public static void main(String[] args) {

        String apiKey = "{OPENAI_API_KEY}";
        String apiUrl = "{OPENAI_API_URL}";

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(
                (request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + apiKey);
                    return execution.execute(request, body);
                }
        );

        ChatHistoryRepository chatHistoryRepository = new ChatHistoryRepository();

        ChatGPTService chatGPTService = new ChatGPTService(restTemplate, apiUrl, chatHistoryRepository);

        //prompt1
        chatGPTService.hitChatGPT("Hi, I'm srinivas, I'm new to the US.");

        //prompt2
        chatGPTService.hitChatGPT("What is the capital city of the country ?");

        //prompt2
        chatGPTService.hitChatGPT("What are the famous places here?");

    }
}
