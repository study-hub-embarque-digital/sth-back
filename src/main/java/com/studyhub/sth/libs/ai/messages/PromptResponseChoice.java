package com.studyhub.sth.libs.ai.messages;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PromptResponseChoice {
    public int index;
    public PromptChoiceMessage message;
    public String finishReason;
}
