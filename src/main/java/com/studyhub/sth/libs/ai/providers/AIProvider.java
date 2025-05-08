package com.studyhub.sth.libs.ai.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.studyhub.sth.libs.ai.messages.PromptRequest;
import com.studyhub.sth.libs.ai.messages.PromptResponse;
import lombok.Getter;

@Getter
public abstract class AIProvider {
    private String baseUrl;
    private String apiKey;
    private ObjectMapper objectMapper;

    public AIProvider(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    public abstract PromptResponse send(PromptRequest request) throws JsonProcessingException;
}
