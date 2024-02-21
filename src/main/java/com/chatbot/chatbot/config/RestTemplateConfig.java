package com.chatbot.chatbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestTemplateConfig {

    @Autowired
    Environment env;

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                (request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + env.getProperty("chatgpt.apikey"));
                    request.getHeaders().add("Content-Type","application/json");
                    return execution.execute(request, body);
                }
        );
        return restTemplate;
    }

}