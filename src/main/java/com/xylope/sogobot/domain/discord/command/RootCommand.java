package com.xylope.sogobot.domain.discord.command;

import com.xylope.sogobot.domain.discord.property.MessageProperties;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class RootCommand extends Command{
    private MessageProperties messageProperties;

    public RootCommand(String prefix, MessageProperties messageProperties) {
        super(prefix);
        this.messageProperties = messageProperties;
    }

    @Override
    public void execute(String[] args, User sender, MessageChannel channel, int depth) {
        super.execute(args, sender, channel, 0); //root command 의 depth 는 항상 0이다
    }

    @Override
    public void run(String[] args, User sender, MessageChannel channel, int depth) {
        //if command's arguments aren't exists
        if(args.length < depth + 2) //root 의 depth 가 0이고, args 의 length 의 기본값이 1이기때문에
            sendBadRequestMessage(channel, "명령어의 인자를 입력해주세요");
        else //if it is command arguments that do not satisfy the trigger
            sendBadRequestMessage(channel, "잘못된 인자값입니다");
    }

    private void sendBadRequestMessage(MessageChannel channel, String reason) {
        MessageEmbed message = new EmbedBuilder()
                .addField(":warning: 잘못된 명령어입니다!", reason, false)
                .setColor(new Color(messageProperties.getError().getColor()))
                .setFooter(messageProperties.getError().getFooter())
                .build();

        channel.sendMessageEmbeds(message).complete();
    }
}
