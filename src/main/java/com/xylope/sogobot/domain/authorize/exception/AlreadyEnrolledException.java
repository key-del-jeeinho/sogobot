package com.xylope.sogobot.domain.authorize.exception;

import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlreadyEnrolledException extends RuntimeException {
    private UnauthorizedUserInfoDto info;
}
