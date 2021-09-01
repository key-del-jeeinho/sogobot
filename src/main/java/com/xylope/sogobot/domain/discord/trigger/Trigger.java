package com.xylope.sogobot.domain.discord.trigger;

@FunctionalInterface
public interface Trigger<T> {
    boolean check(T target);
}
