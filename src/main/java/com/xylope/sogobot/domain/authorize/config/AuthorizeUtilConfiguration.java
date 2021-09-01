package com.xylope.sogobot.domain.authorize.config;

import com.xylope.sogobot.domain.authorize.util.AuthorizeLinkGeneratorUtil;
import com.xylope.sogobot.domain.authorize.util.AuthorizeTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizeUtilConfiguration {
    private AuthorizeTokenUtil authorizeTokenUtil;
    @Bean
    public AuthorizeTokenUtil authorizeTokenUtil() {
        if(authorizeTokenUtil == null)
            authorizeTokenUtil = new AuthorizeTokenUtil();
        return authorizeTokenUtil;
    }

    @Bean
    public AuthorizeLinkGeneratorUtil authorizeLinkGeneratorUtil() {
        return new AuthorizeLinkGeneratorUtil(authorizeTokenUtil());
    }
}
