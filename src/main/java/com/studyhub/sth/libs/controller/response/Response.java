package com.studyhub.sth.libs.controller.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class Response {
    private final String status;
    private final int statusCode;
    private final String message;
    private final RequestMeta meta;

    public Response(HttpStatus statusCode, String message) {
        this.status = statusCode.getReasonPhrase();
        this.statusCode = statusCode.value();
        this.message = message.isEmpty() ? selectMessageByStatusCode(statusCode) : message;
        this.meta = new RequestMeta();
    }

    private String selectMessageByStatusCode(HttpStatus status) {
        if (status.isError()) return "Erro ao processar solicitação.";

        return "Sucesso no processamento da requisição";
    }
}
