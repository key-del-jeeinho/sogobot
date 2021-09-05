package com.xylope.sogobot.domain.discord.manager;

import com.xylope.sogobot.domain.discord.SogoBot;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class DiscordMessageManager {
    @Setter
    private static SogoBot sogoBot;

    public static void sendPrivateMessage(Long userId, MessageEmbed message) {
        sogoBot.doWithJda(jda -> {
            log.info(String.valueOf(userId));
            jda.retrieveUserById(userId).complete().openPrivateChannel().complete()
                    .sendMessageEmbeds(message).complete();
        });
    }
}
