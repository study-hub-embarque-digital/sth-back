package com.studyhub.sth.libs.ai.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public abstract class PromptRequest {
    @JsonProperty("messages")
    private List<PromptMessage> messages;

    @JsonProperty("frequency_penalty")
    private int frequencyPenalty;

    @JsonProperty("max_tokens")
    private int maxTokens;

    @JsonProperty("presence_penalty")
    private int presencePenalty;

    @JsonProperty("response_format")
    private PromptResponseFormat responseFormat;

    @JsonProperty("stop")
    private Object stop;

    @JsonProperty("stream")
    private boolean stream;

    @JsonProperty("stream_options")
    private Object streamOptions;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("top_p")
    private double topP;

    @JsonProperty("tools")
    private Object tools;

    @JsonProperty("tool_choice")
    private String toolChoice;

    @JsonProperty("logprobs")
    private boolean logprobs;

    @JsonProperty("top_logprobs")
    private Object topLogprobs;
}
