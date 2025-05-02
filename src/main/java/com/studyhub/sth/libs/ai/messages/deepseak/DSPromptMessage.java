package com.studyhub.sth.libs.ai.messages.deepseak;

import com.studyhub.sth.libs.ai.exceptions.MessageRoleNotSupportedException;
import com.studyhub.sth.libs.ai.messages.PromptMessage;
import com.studyhub.sth.libs.ai.messages.PromptMessageRole;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class DSPromptMessage extends PromptMessage {
    public DSPromptMessage(String content, PromptMessageRole role) {
        super(content, role);
        if (role != PromptMessageRole.system && role != PromptMessageRole.user) throw new MessageRoleNotSupportedException(role);
    }
}
