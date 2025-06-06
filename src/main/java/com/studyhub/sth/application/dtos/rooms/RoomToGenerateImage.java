package com.studyhub.sth.application.dtos.rooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
public class RoomToGenerateImage {
    @JsonProperty("name")
    private String name;

    @JsonProperty("main_stack")
    private String mainStack;

    @JsonProperty("background_color")
    private String backgroundColor;

    @JsonProperty("text_color")
    private String textColor;

    public RoomToGenerateImage(String name, String mainStack, String backgroundColor, String textColor) {
        this.name = name;
        this.mainStack = mainStack;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }
}
