package com.studyhub.sth.libs.ai.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PromptUsage {
    @JsonProperty("prompt_tokens")
    public int promptTokens;
    @JsonProperty("completion_tokens")
    public int completionTokens;
    @JsonProperty("total_tokens")
    public int totalTokens;
}
