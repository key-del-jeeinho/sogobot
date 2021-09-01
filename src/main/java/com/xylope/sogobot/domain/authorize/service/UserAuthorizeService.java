package com.xylope.sogobot.domain.authorize.service;

import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;

public interface UserAuthorizeService {
    void authorize(UnauthorizedUserInfoDto info);
}
