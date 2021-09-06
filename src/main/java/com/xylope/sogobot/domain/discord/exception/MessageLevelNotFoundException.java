package com.xylope.sogobot.domain.discord.exception;

public class MessageLevelNotFoundException extends RuntimeException {
    private String level;
    private Integer id;

    public MessageLevelNotFoundException(String level) {
        this.level = level;
    }

    public MessageLevelNotFoundException(Integer id) {
        this.id = id;
    }
}
