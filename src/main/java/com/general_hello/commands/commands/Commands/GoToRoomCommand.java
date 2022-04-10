package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

public class GoToRoomCommand extends Command {
    public GoToRoomCommand() {
        // Name and help
        this.name = "go-through-door";
        this.help = "Transfer rooms";
        // Cool down in seconds (Unnecessary but up to you)
        this.cooldown = 10;
        // Enabled roles to use this command
        this.requiredRole = Config.get("role");
    }

    @Override
    protected void execute(CommandEvent event) {
        try {
            // Changing channel permissions
            String room = event.getArgs();
            TextChannel textChannel;
            try {
                textChannel = event.getJDA().getTextChannelById(Integer.parseInt(room));
            } catch (Exception e) {
                textChannel = event.getJDA().getTextChannelsByName(room, true).get(0);
            }
            textChannel.upsertPermissionOverride(event.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
            event.getTextChannel().upsertPermissionOverride(event.getMember()).setDeny(Permission.VIEW_CHANNEL).queue();
            // The reply
            event.reply("Done transferring!");
        } catch (Exception e) {
            event.reply("You placed the wrong or invalid channel name or id");
        }
    }
}
