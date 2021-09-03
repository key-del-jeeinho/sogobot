package com.xylope.sogobot.domain.authorize.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainNotFoundException extends RuntimeException {
    private final String domain;
}
