package com.studyhub.sth.application.dtos.jitsi;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JitsiContext {
    private JitsiFeature features;
    private JitsiUser user;
}
