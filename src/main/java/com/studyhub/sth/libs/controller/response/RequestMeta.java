package com.studyhub.sth.libs.controller.response;

import lombok.Getter;

import java.util.Date;

@Getter
public class RequestMeta {
    private final Date timestamp;

    public RequestMeta() {
        this.timestamp = new Date();
    }
}
