package com.xylope.sogobot.domain.discord.exception;

import javax.security.auth.login.LoginException;

public class BotCreationFailureException extends RuntimeException {
    public BotCreationFailureException(LoginException e) {
        super(e);
    }
}
