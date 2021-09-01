package com.xylope.sogobot.domain.authorize.exception;

import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class EmailAlreadyEnrolledException extends RuntimeException {
    private final UnauthorizedUserInfoDto info;
}
