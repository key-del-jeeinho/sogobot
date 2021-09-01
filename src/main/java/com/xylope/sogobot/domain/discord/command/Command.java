package com.xylope.sogobot.domain.discord.command;

import com.xylope.sogobot.domain.discord.trigger.CommandTrigger;
import com.xylope.sogobot.domain.discord.exception.ChildCommandAlreadyExistsException;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private final CommandTrigger trigger;
    private final List<Command> children;

    public Command(String prefix) {
        this(new CommandTrigger(prefix));
    }

    public Command(CommandTrigger trigger) {
        this.trigger = trigger;
        this.children = new ArrayList<>();
    }

    public void addChild(Command child) {
        if(children.contains(child))
            throw new ChildCommandAlreadyExistsException();
        children.add(child);
    }

    public void execute(String[] args, User sender, TextChannel channel, int depth) {
        if(trigger.check(args[depth])) { //double check (super command 의 command.trigger.check())
            if(args.length > depth+1) {
                for (Command command : children) {
                    if (command.trigger.check(args[depth + 1])) {
                        command.execute(args, sender, channel, ++depth);
                        return;
                    }
                }
            }
            run(args, sender, channel, depth);
        }
    }

    public abstract void run(String[] args, User sender, TextChannel channel, int depth);
}
