package com.xylope.sogobot.domain.authorize.exception;

import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class DomainNotFoundException extends RuntimeException {
    private final String domain;
    @Setter
    private UnauthorizedUserInfoDto info;
}
