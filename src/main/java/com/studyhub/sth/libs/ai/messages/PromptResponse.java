package com.studyhub.sth.libs.ai.messages;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public abstract class PromptResponse {
    public String id;
    public String object;
    public long created;
    public String model;
    public List<PromptResponseChoice> choices;
    public PromptUsage usage;
    public String systemFingerprint;
}
