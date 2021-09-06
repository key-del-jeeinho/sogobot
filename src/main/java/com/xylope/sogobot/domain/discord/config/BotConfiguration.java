package com.xylope.sogobot.domain.discord.config;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.domain.discord.command.RootCommand;
import com.xylope.sogobot.domain.discord.command.function.AuthorizeCommand;
import com.xylope.sogobot.domain.discord.command.function.SelfCheckEnrollCommand;
import com.xylope.sogobot.domain.discord.listeners.CommandListener;
import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import com.xylope.sogobot.domain.discord.manager.DiscordMessageManager;
import com.xylope.sogobot.domain.discord.manager.DiscordRoleManager;
import com.xylope.sogobot.domain.discord.property.BotProperties;
import com.xylope.sogobot.domain.discord.property.MessageProperties;
import com.xylope.sogobot.domain.enroll.repository.UserRepository;
import com.xylope.sogobot.domain.selfcheck.service.SelfCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration @RequiredArgsConstructor
public class BotConfiguration {
    private SogoBot sogoBot;
    private final UserAuthorizeService authorizeService;
    private final MessageProperties messageProperties;
    private final BotProperties botProperties;
    private final UserRepository userRepository;
    private final SelfCheckService selfCheckService;

    @Bean
    public SogoBot sogoBot() {
        if(sogoBot == null) {
            sogoBot = new SogoBot(botProperties.getToken());
            List<BotProperties.DepartmentRole> roles = botProperties.getRoles();
            for(BotProperties.DepartmentRole role : roles) {
                role.getDepartment().setRoleId(role.getRoleId());
            }
        }
        //ManagerInitializer
        DiscordRoleManager.setSogoBot(sogoBot);
        DiscordMessageManager.setSogoBot(sogoBot);
        return sogoBot;
    }

    @Bean
    public RootCommand rootCommand() {
        RootCommand rootCommand = new RootCommand(botProperties.getCommandPrefix(), messageProperties);

        LeafCommand authorizeCommand = new AuthorizeCommand("인증", messageProperties, authorizeService);
        LeafCommand selfCheckEnrollCommand = new SelfCheckEnrollCommand("진단등록", messageProperties, userRepository, selfCheckService);

        rootCommand.addChild(authorizeCommand);
        rootCommand.addChild(selfCheckEnrollCommand);

        return rootCommand;
    }

    @Bean
    public CommandListener commandListener() {
        return new CommandListener(rootCommand(), sogoBot);
    }

}
