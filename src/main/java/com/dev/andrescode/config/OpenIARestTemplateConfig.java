package com.dev.andrescode.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenIARestTemplateConfig {

    @Value("${openai.api.key}")
    private String openaiApiKey;


    @Bean
    @Qualifier("openiaRestTemplate")
    public RestTemplate openiaRestTemplate(){

        RestTemplate restTemplate= new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            request.getHeaders().add("OpenAI-Organization","org-OFK0JXheaFViG5J7RtH7nxom");
            return execution.execute(request, body);
        });
        return restTemplate;

    }

}
