package com.studyhub.sth.application.dtos.jitsi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JitsiUser {
    @JsonProperty("hidden-from-recorder")
    private boolean hiddenFromRecorder;
    private boolean moderator;
    private String name;
    private String id;
    private String avatar;
    private String email;
}
