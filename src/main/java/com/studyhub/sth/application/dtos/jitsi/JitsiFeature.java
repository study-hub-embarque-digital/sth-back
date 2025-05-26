package com.studyhub.sth.application.dtos.jitsi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JitsiFeature {
    @JsonProperty("outbound-call")
    private boolean outboundCall;
    @JsonProperty("sip-outbound-call")
    private boolean sipOutboundCall;
    private boolean transcription;
    private boolean recording;
    private boolean livestreaming;
}
