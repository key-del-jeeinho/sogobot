package com.xylope.sogobot.domain.discord.config;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.domain.discord.command.RootCommand;
import com.xylope.sogobot.domain.discord.command.function.AuthorizeCommand;
import com.xylope.sogobot.domain.discord.command.function.TestCommand;
import com.xylope.sogobot.domain.discord.listeners.CommandListener;
import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import com.xylope.sogobot.domain.discord.manager.DiscordRoleManager;
import com.xylope.sogobot.domain.discord.property.BotProperties;
import com.xylope.sogobot.domain.discord.property.MessageProperties;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration @RequiredArgsConstructor
public class BotConfiguration {
    private SogoBot sogoBot;
    private final UserAuthorizeService authorizeService;
    private final MessageProperties messageProperties;
    private final BotProperties botProperties;

    @Bean
    public SogoBot sogoBot() {
        if(sogoBot == null) {
            sogoBot = new SogoBot(botProperties.getToken());
            List<BotProperties.DepartmentRole> roles = botProperties.getRoles();
            for(BotProperties.DepartmentRole role : roles) {
                role.getDepartment().setRoleId(role.getRoleId());
            }
        }
        DiscordRoleManager.setSogoBot(sogoBot);
        return sogoBot;
    }

    @Bean
    public RootCommand rootCommand() {
        RootCommand rootCommand = new RootCommand(botProperties.getCommandPrefix(), messageProperties);

        LeafCommand testCommand = new TestCommand("테스트");
        LeafCommand authorizeCommand = new AuthorizeCommand("인증", messageProperties, authorizeService);
        rootCommand.addChild(testCommand);
        rootCommand.addChild(authorizeCommand);

        return rootCommand;
    }

    @Bean
    public CommandListener commandListener() {
        return new CommandListener(rootCommand(), sogoBot);
    }

}
