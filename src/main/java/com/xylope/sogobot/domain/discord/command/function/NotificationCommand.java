package com.xylope.sogobot.domain.discord.command.function;

import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.domain.discord.service.NotificationService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class NotificationCommand extends LeafCommand {
    private final NotificationService notificationService;

    public NotificationCommand(String prefix, NotificationService notificationService) {
        super(prefix);
        this.notificationService = notificationService;
    }

    @Override //소고야 공지 제목 내용 발송대상(모든서버 | 모든유저 | 공식서버 | 공식서버유저 | 등록유저)
    public void run(String[] args, User sender, MessageChannel channel, int depth) {
        String title = args[depth + 1];
        String content = args[depth + 2];
        String targetStr = args[depth + 3];
        notificationService.sendNotification(title, content, targetStr);
    }
}
