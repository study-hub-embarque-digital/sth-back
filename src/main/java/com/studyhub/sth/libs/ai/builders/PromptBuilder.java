package com.studyhub.sth.libs.ai.builders;

import com.studyhub.sth.libs.ai.messages.PromptMessage;
import com.studyhub.sth.libs.ai.messages.PromptRequest;
import com.studyhub.sth.libs.ai.messages.PromptResponseFormat;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public abstract class PromptBuilder {
    private List<PromptMessage> messages;
    private int frequencyPenalty;
    private int maxTokens;
    private int presencePenalty;
    private PromptResponseFormat responseFormat;
    private Object stop;
    private boolean stream;
    private Object streamOptions;
    private double temperature;
    private double topP;
    private Object tools;
    private String toolChoice;
    private boolean logprobs;
    private Object topLogprobs;

    public abstract PromptRequest buildRequest();
}
