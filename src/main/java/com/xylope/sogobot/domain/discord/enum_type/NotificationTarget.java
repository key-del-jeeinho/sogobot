package com.xylope.sogobot.domain.discord.enum_type;

import com.xylope.sogobot.domain.discord.exception.NotificationTargetNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NotificationTarget {
    SERVER_ALL("모든서버"),
    SERVER_OFFICIAL("공식서버"),
    USER_ALL("모든유저"),
    USER_OFFICIAL("공식서버유저"),
    USER_ENROLLED("등록유저");

    public static NotificationTarget of(String prefix) {
        for (NotificationTarget value : NotificationTarget.values()) {
            if(value.prefix.equals(prefix)) return value;
        }
        throw new NotificationTargetNotFoundException(prefix);
    }

    private final String prefix;
}
