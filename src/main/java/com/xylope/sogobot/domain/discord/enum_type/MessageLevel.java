package com.xylope.sogobot.domain.discord.enum_type;

import com.xylope.sogobot.domain.discord.exception.MessageLevelNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public enum MessageLevel {
    TRACE(0), INFO(1), WARN(2), ERROR(3);

    private final Integer id;

    public static MessageLevel of(String level) {
        for (MessageLevel value : values())
            if(value.name().equals(level))
                return value;
        throw new MessageLevelNotFoundException(level);
    }

    public static MessageLevel of(Integer id) {
        for(MessageLevel value : values())
            if(value.getId().equals(id))
                return value;
        throw new MessageLevelNotFoundException(id);
    }
}
