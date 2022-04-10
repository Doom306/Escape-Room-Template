package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import com.general_hello.commands.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.xdrop.fuzzywuzzy.FuzzySearch;

public class OpenCommand extends Command {
    public OpenCommand() {
        // Name and help
        this.name = "open";
        this.help = "Opens a location or something";
        // The role that is allowed to use this command
        this.requiredRole = Config.get("role");
    }

    @Override
    protected void execute(CommandEvent event) {
        // Get the location
        String item = event.getArgs();
        String result = FuzzySearch.extractTop(item, Bot.locationNames, 1).get(0).getString();
        Location location = Bot.stringToLocation.get(result);
        // Checkers and replies
        if (!location.getTextChannel().equals(event.getTextChannel())) {
            event.reply("Can't do it in this channel. It has to be in " + location.getTextChannel().getAsMention());
            return;
        }
        if (!Bot.userInventory.containsKey(event.getAuthor())) {
            event.reply(location.getErrorResponse());
            return;
        }
        if (Bot.userInventory.get(event.getAuthor()).contains(location.getItemRequired())) {
            event.reply(location.getResponse());
            return;
        }

        // Reply
        event.reply(location.getErrorResponse());
    }
}
