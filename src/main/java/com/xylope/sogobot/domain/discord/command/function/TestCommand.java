package com.xylope.sogobot.domain.discord.command.function;

import com.xylope.sogobot.domain.discord.command.LeafCommand;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.util.Arrays;

public class TestCommand extends LeafCommand {
    public TestCommand(String prefix) {
        super(prefix);
    }

    @Override
    public void run(String[] args, User sender, TextChannel channel, int depth) {
        channel.sendMessage(String.format("%s 님이 %s 명령어로 test command 를 실행시키셧습니다! (depth : %d)",
                sender.getAsMention(),
                MarkdownUtil.codeblock(Arrays.toString(args))
                ,depth))
                .complete();
    }
}
