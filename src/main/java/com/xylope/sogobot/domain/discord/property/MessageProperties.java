package com.xylope.sogobot.domain.discord.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "message")
public class MessageProperties {
    private MessageInfo info;
    private MessageInfo error;

    @Getter @Setter
    public static class MessageInfo {
        private int color;
        private String footer;
    }
}
