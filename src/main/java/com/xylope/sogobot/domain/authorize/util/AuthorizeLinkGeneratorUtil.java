package com.xylope.sogobot.domain.authorize.util;

import com.xylope.sogobot.global.dto.DomainAuthorizedUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class AuthorizeLinkGeneratorUtil {
    private final AuthorizeTokenUtil authorizeTokenUtil;
    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    public String generateLink(DomainAuthorizedUserInfoDto user) {
        final String URL = serverAddress + ":" + serverPort + "/authorize";
        String token = authorizeTokenUtil.makeJwtToken(user);
        return URL + "?token=" + token;
    }
}
