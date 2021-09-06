package com.xylope.sogobot.domain.discord.service;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.domain.discord.dto.MessageDto;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessagingService {
    private final SogoBot sogoBot;
    private Map<MessageChannel, MessageEmbed> embedMessageQueue;
    private Map<MessageChannel, String> messageQueue;

    public MessagingService(SogoBot sogoBot) {
        this.sogoBot = sogoBot;
        embedMessageQueue = new HashMap<>();
        messageQueue = new HashMap<>();
    }

    public void sendMessage(MessageChannel channel, MessageDto message) {
        embedMessageQueue.put(channel, message);
    }
}
