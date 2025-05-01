package com.studyhub.sth.libs.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studyhub.sth.libs.ai.builders.PromptBuilder;
import com.studyhub.sth.libs.ai.messages.PromptRequest;
import com.studyhub.sth.libs.ai.messages.PromptResponse;
import com.studyhub.sth.libs.ai.providers.AIProvider;

public class AIClient {
    private final AIProvider provider;

    public AIClient(AIProvider provider) {
        this.provider = provider;
    }

    public PromptResponse send(PromptBuilder builder) throws JsonProcessingException {
        PromptRequest request = builder.buildRequest();
        return provider.send(request);
    }
}
