package com.studyhub.sth.libs.ai.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
public class PromptResponseFormat {
    @JsonProperty("type")
    private String type;
}
