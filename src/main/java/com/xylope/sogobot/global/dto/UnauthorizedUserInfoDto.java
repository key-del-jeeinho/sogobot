package com.xylope.sogobot.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnauthorizedUserInfoDto {
    private final Long id;
    private final String name;
    private final String email;
}
