package com.xylope.sogobot.domain.discord.trigger;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandTrigger implements Trigger<String>{
    private final String prefix;
    @Override
    public boolean check(String target) {
        return target.equals(prefix);
    }
}
