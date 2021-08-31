package com.xylope.sogobot.infra.exception;

public class EmailSendingFailureException extends RuntimeException {
    public EmailSendingFailureException(Throwable cause) {
        super(cause);
    }
}
