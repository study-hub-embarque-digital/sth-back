package com.studyhub.sth.application.dtos.users;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.studyhub.sth.application.dtos.reunioes.ReuniaoDto;

public class SubWSMessage {
    private String context;
    private ReuniaoDto additionalData;

    public SubWSMessage(String context, ReuniaoDto additionalData) {
        this.context = context;
        this.additionalData = additionalData;
    }
}
