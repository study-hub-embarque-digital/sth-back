package com.studyhub.sth.libs.ai.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studyhub.sth.libs.ai.messages.PromptRequest;
import com.studyhub.sth.libs.ai.messages.PromptResponse;
import com.studyhub.sth.libs.ai.messages.deepseak.DSPromptResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DeepSeakProvider extends AIProvider {
    public DeepSeakProvider(String apiKey) {
        super("https://api.deepseek.com", apiKey);
    }

    @Override
    public PromptResponse send(PromptRequest request) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setBearerAuth(this.getApiKey());

        HttpEntity<PromptRequest> entity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<DSPromptResponse> responseEntity = restTemplate.exchange(
                this.getBaseUrl() + "/chat/completions", HttpMethod.POST, entity, DSPromptResponse.class);

        return responseEntity.getBody();
    }
}
