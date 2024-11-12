package com.jagrosh.jmusicbot.commands.fun;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AutoReply extends ListenerAdapter {
    private final String targetUserId = "447671290426294273";
    private final String responseText = "Zamknij pizde lokiec";

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (!event.getAuthor().isBot() && event.getAuthor().getId().equals(targetUserId))
        {
            event.getChannel().sendMessage(responseText).queue();
        }
    }

}
