package com.xylope.sogobot.domain.discord;

import com.xylope.sogobot.domain.discord.exception.BotCreationFailureException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.function.Consumer;

public class SogoBot {
    private final JDA jda;

    public SogoBot(final String token) {
        try {
            jda = JDABuilder
                    .createDefault(token)
                    .build();
        } catch (LoginException e) {
            throw new BotCreationFailureException(e);
        }
    }

    public void addEventListener(ListenerAdapter eventListener) {
        jda.addEventListener(eventListener);
    }

    public void doWithJda(Consumer<JDA> consumer) {
        consumer.accept(jda);
    }
}
