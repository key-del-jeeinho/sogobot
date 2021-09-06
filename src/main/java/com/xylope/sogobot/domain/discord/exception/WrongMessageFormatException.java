package com.xylope.sogobot.domain.discord.exception;

public class WrongMessageFormatException extends RuntimeException {
    public WrongMessageFormatException(String message) {
        super(message);
    }
}
