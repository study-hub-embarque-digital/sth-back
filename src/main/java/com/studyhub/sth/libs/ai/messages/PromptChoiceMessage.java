package com.studyhub.sth.libs.ai.messages;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PromptChoiceMessage {
    private String content;
    private String role;
}
