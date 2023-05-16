package com.dev.andrescode.controller;


import com.dev.andrescode.dto.ChatRequest;
import com.dev.andrescode.dto.ChatResponse;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/v1")
public class ChatController {

    @Autowired
    private ChatgptService chatgptService;

    @Qualifier("openaiRestTemplate")
    private final RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);

        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.choices() == null || response.choices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.choices().get(0).message().content();
    }

    @GetMapping("/test")
    public void test(){
        String responseMessage = chatgptService.multiChat(Arrays.asList(new MultiChatMessage("user","how are you?")));
        System.out.print(responseMessage); //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
    }

    public void test2(){
        String responseMessage = chatgptService.sendMessage("how are you");
        System.out.print(responseMessage); //I'm doing well, thank you. How about you?
    }
}
