package com.xylope.sogobot.domain.authorize.controller;

import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import com.xylope.sogobot.domain.authorize.util.AuthorizeTokenUtil;
import com.xylope.sogobot.domain.enroll.service.EnrollService;
import com.xylope.sogobot.global.dto.AuthorizedUserDto;
import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

@Controller @RequiredArgsConstructor
public class AuthorizeController {
    private final AuthorizeTokenUtil authorizeTokenUtil;
    private final UserAuthorizeService userAuthorizeService;
    private final EnrollService enrollService;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam String token) {
        AuthorizedUserDto user = authorizeTokenUtil.getUserByToken(token);

        enrollService.enrollUser(user);
        return "authorize";
    }

    public void startAuthorize(UnauthorizedUserInfoDto dto) {
        userAuthorizeService.authorize(dto);
    }
}
