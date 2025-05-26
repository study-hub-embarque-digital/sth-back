package com.studyhub.sth.libs.ai.messages.deepseak;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.studyhub.sth.libs.ai.messages.PromptMessage;
import com.studyhub.sth.libs.ai.messages.PromptRequest;
import com.studyhub.sth.libs.ai.messages.PromptResponseFormat;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class DSPromptRequest extends PromptRequest {
    @JsonProperty("model")
    public String model;

    public DSPromptRequest(List<PromptMessage> messages, int frequencyPenalty, int maxTokens, int presencePenalty, PromptResponseFormat responseFormat, Object stop, boolean stream, Object streamOptions, double temperature, double topP, Object tools, String toolChoice, boolean logprobs, Object topLogprobs, String model) {
        super(messages, frequencyPenalty, maxTokens, presencePenalty, responseFormat, stop, stream, streamOptions, temperature, topP, tools, toolChoice, logprobs, topLogprobs);
        this.model = model;
    }
}
