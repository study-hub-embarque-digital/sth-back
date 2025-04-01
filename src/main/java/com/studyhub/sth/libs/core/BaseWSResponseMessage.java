package com.studyhub.sth.libs.core;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseWSResponseMessage<TEnum extends Enum<TEnum>> {
    private WSMessageContext context;
    private Enum<TEnum> message;
}
