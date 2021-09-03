package com.xylope.sogobot.domain.authorize.controller;

import com.xylope.sogobot.domain.authorize.exception.AlreadyEnrolledException;
import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import com.xylope.sogobot.domain.authorize.util.AuthorizeTokenUtil;
import com.xylope.sogobot.domain.enroll.service.EnrollService;
import com.xylope.sogobot.global.dto.AuthorizedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @RequiredArgsConstructor
public class AuthorizeController {
    private final AuthorizeTokenUtil authorizeTokenUtil;
    private final EnrollService enrollService;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam String token) {
        AuthorizedUserDto user = authorizeTokenUtil.getUserByToken(token);
        enrollService.enrollUser(user);
        return "authorize";
    }
}
