package com.xylope.sogobot.domain.discord.command;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class LeafCommand extends Command{
    public LeafCommand(String prefix) {
        super(prefix);
    }

    @Override
    public void execute(String[] args, User sender, MessageChannel channel, int depth) {
        run(args, sender, channel, depth);
    }

    @Override
    public abstract void run(String[] args, User sender, MessageChannel channel, int depth);
}
