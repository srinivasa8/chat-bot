package com.chatbot.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestTemplateConfig {

    @Value("${openai.api.key}")
    private String apiKey;

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                (request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + apiKey);
                    return execution.execute(request, body);
                }
        );
        return restTemplate;
    }

}