package com.xylope.sogobot.domain.discord.listeners;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.domain.discord.command.RootCommand;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class CommandListener extends ListenerAdapter {
    private final RootCommand root;

    public CommandListener(RootCommand root, SogoBot sogoBot) {
        sogoBot.addEventListener(this);
        this.root = root;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;

        String[] args = event.getMessage().getContentRaw().split(" ");
        User user = event.getAuthor();
        MessageChannel channel = event.getChannel();

        root.execute(args, user, channel, 0);
    }
}
