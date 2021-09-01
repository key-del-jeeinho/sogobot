package com.xylope.sogobot.domain.discord.config;

import com.xylope.sogobot.domain.authorize.controller.AuthorizeController;
import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.domain.discord.command.RootCommand;
import com.xylope.sogobot.domain.discord.command.function.AuthorizeCommand;
import com.xylope.sogobot.domain.discord.command.function.TestCommand;
import com.xylope.sogobot.domain.discord.listeners.CommandListener;
import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @RequiredArgsConstructor
public class BotConfiguration {
    @Value("${bot.token}") private String token;
    @Value("${bot.command-prefix}") private String commandPrefix;
    private SogoBot sogoBot;
    private final AuthorizeController authorizeController;

    @Bean
    public SogoBot sogoBot() {
        if(sogoBot == null) {
            sogoBot = new SogoBot(token);
        }

        return sogoBot;
    }

    @Bean
    public RootCommand rootCommand() {
        RootCommand rootCommand = new RootCommand(commandPrefix);

        LeafCommand testCommand = new TestCommand("테스트");
        LeafCommand authorizeCommand = new AuthorizeCommand("인증", authorizeController);
        rootCommand.addChild(testCommand);
        rootCommand.addChild(authorizeCommand);

        return rootCommand;
    }

    @Bean
    public CommandListener commandListener() {
        return new CommandListener(rootCommand(), sogoBot);
    }
}
