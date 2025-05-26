package com.studyhub.sth.libs.ai.messages;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public abstract class PromptMessage {
    private String content;
    private String role;

    public PromptMessage(String content, PromptMessageRole role) {
        this.content = content;
        this.role = role.name();
    }
}
