package com.xylope.sogobot.domain.discord.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "bot")
public class BotProperties {
    private String token;
    private String commandPrefix;
    private String adminEmail;
}
