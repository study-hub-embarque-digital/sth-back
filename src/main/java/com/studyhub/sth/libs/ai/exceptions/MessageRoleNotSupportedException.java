package com.studyhub.sth.libs.ai.exceptions;

import com.studyhub.sth.libs.ai.messages.PromptMessageRole;

public class MessageRoleNotSupportedException extends RuntimeException {
    public MessageRoleNotSupportedException(PromptMessageRole role) {
        super("Message Role [" + role.name() + "] not supported by selected provider. See docs of provider to select a valid message role.");
    }
}
