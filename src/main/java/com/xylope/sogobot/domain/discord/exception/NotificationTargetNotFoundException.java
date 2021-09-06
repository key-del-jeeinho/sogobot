package com.xylope.sogobot.domain.discord.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class NotificationTargetNotFoundException extends RuntimeException {
    private final String prefix;
}
