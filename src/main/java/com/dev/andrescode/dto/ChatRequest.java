package com.dev.andrescode.dto;

import java.util.ArrayList;
import java.util.List;

public record ChatRequest(String model, List<Message> messages, int n, double temperature) {

    public ChatRequest(String model, String prompt){

        this(model,new ArrayList<>(), 0,0.7);

        this.messages.add(new Message("user", prompt));

    }
}
