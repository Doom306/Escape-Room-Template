package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Collections;

public class GiveCommand extends Command {
    public GiveCommand() {
        // Name and help info
        this.name = "give";
        this.help = "Give an item to the user (Format is ITEM then USER MENTIONED)";
        // If you want it to be an owner command
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            // Gets the mentioned user
            User user;
            if (event.getMessage().getMentionedUsers().isEmpty()) {
                user = event.getAuthor();
            } else {
                user = event.getMessage().getMentionedUsers().get(0);
            }

            // They might place the wrong item. (Just incase)
            String item = event.getArgs().split("\\s+")[0];
            String result = FuzzySearch.extractTop(item, Bot.itemNames, 1).get(0).getString();

            // Adding it to a hashmap
            if (Bot.userInventory.containsKey(user)) {
                ArrayList<Item> itemsOwned = Bot.userInventory.get(user);
                itemsOwned.add(Bot.stringToItem.get(result));
                Bot.userInventory.put(user, itemsOwned);
            } else {
                Bot.userInventory.put(user, new ArrayList<>(Collections.singletonList(Bot.stringToItem.get(result))));
            }

            // Reply
            event.reply(user.getAsMention() + " now has a(n) " + result);
        } catch (Exception e) {
            event.reply("Something is wrong in your command.");
        }
    }
}
