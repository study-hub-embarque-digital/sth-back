package com.studyhub.sth.libs.core.messaging.socket;

public abstract class SocketMessage<TEnum extends Enum<TEnum>, UEnum extends Enum<UEnum>> {
    private Enum<TEnum> context;
    private Enum<UEnum> message;
}
