package com.xylope.sogobot.domain.discord.service;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.domain.discord.enum_type.NotificationTarget;
import com.xylope.sogobot.domain.discord.property.MessageProperties;
import com.xylope.sogobot.domain.enroll.repository.UserRepository;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor @Service
public class NotificationService {
    private final SogoBot sogoBot;
    private final UserRepository userRepository;
    @Value("${bot.server-id}")
    private String serverId;
    private final MessageProperties messageProperties;

    public void sendNotification(String title, String content, String targetStr) {
        NotificationTarget target = NotificationTarget.of(targetStr);

        List<MessageChannel> receiver = getReceiver(target);
        receiver.forEach(channel -> channel.sendMessageEmbeds(new EmbedBuilder()
                .setTitle("새로운 공지가 도착했어요!")
                .addField(title, content, false)
                .setColor(messageProperties.getInfo().getColor())
                .setFooter(messageProperties.getInfo().getFooter())
                .build()).complete());
    }

    private List<MessageChannel> getReceiver(NotificationTarget target) {
        List<MessageChannel> receiver = new ArrayList<>();

        sogoBot.doWithJda(jda -> {
            switch (target) {
                case SERVER_ALL:
                    jda.getGuilds().forEach(guild ->
                            receiver.add(getNotificationChannel(guild)));
                    break;
                case SERVER_OFFICIAL: {
                    Guild guild = jda.getGuildById(serverId);
                    TextChannel channel = getNotificationChannel(guild);
                    receiver.add(channel);
                    break;
                }
                case USER_ALL: {
                    jda.getUsers().forEach(user ->
                            receiver.add(user.openPrivateChannel().complete()));
                    break;
                }
                case USER_OFFICIAL: {
                    Guild guild = Objects.requireNonNull(jda.getGuildById(serverId));
                    guild.getMembers().forEach(member -> receiver.add(member.getUser().openPrivateChannel().complete()));
                    break;
                }
                case USER_ENROLLED: {
                    for (DepartmentType value : DepartmentType.values()) {
                        userRepository.getAllByDepartmentType(value.name()).forEach(user -> {
                            User member = Objects.requireNonNull(jda.getUserById(user.getId()));
                            receiver.add(
                                    member.openPrivateChannel().complete());
                        });
                    }
                    break;
                }
            }
        });
        return receiver;
    }

    private TextChannel getNotificationChannel(Guild guild) {
        TextChannel channel;

        //rules channel -> default channel -> system channel
        if((channel = guild.getRulesChannel()) == null)
            if((channel = guild.getDefaultChannel()) == null)
                channel = guild.getSystemChannel();

        return channel;
    }
}
