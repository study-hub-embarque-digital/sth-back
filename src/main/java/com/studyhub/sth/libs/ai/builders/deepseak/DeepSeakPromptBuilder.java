package com.studyhub.sth.libs.ai.builders.deepseak;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.studyhub.sth.libs.ai.builders.PromptBuilder;
import com.studyhub.sth.libs.ai.messages.PromptRequest;
import com.studyhub.sth.libs.ai.messages.deepseak.DSPromptRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DeepSeakPromptBuilder extends PromptBuilder {
    @JsonProperty("model")
    public String model;

    public PromptRequest buildRequest() {
        DSPromptRequest dsPromptRequest = new DSPromptRequest(this.getMessages(), this.getFrequencyPenalty(), this.getMaxTokens(), this.getPresencePenalty(), this.getResponseFormat(), this.getStop(), this.isStream(), this.getStreamOptions(), this.getTemperature(), this.getTopP(), this.getTools(), this.getToolChoice(), this.isLogprobs(), this.getTopLogprobs(), this.model);

        return dsPromptRequest;
    }
}
