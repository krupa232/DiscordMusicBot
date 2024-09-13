package com.jagrosh.jmusicbot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;

public class TestCommand extends Command {

    public TestCommand(Bot bot) {
        this.name = "test";
        this.help = "some funny command";
    }

    @Override
    protected void execute (CommandEvent event) {
        event.reply("Test " + event.getAuthor().getAsMention() + " test");
    }
}
