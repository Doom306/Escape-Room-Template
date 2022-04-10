package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Config;
import com.general_hello.commands.commands.Utils.EmbedUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class ViewCommand extends Command {
    // The message (You can edit this if you want etc)
    private static String mazeEmojis = """
            :red_square::red_square::red_square::red_square::red_square:
            :red_square::red_square::green_square::red_square::red_square:
            :red_square::green_square::green_square::green_square::red_square:
            :green_square::green_square::green_square::green_square::green_square:""";
    public ViewCommand() {
        // Name and help command
        this.name = "view";
        this.help = "Views a maze (Editable with code)";
        // People with the role that can use this command
        this.requiredRole = Config.get("role");
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getAuthor().getIdLong() == 260773625957842944L) {
            // Simple sending
            event.getChannel().sendMessageEmbeds(EmbedUtil.defaultEmbed(mazeEmojis, Color.ORANGE)).setActionRows(ActionRow.of(
                    Button.secondary("idk", Emoji.fromMarkdown("<:first:930264043564961822>")),
                    Button.secondary("idak", Emoji.fromMarkdown("<:last:930264202331975701>"))
            )).queue();
        } else if (event.getAuthor().getIdLong() == 959233424890142801L) {
            // Simple sending
            event.getChannel().sendMessageEmbeds(EmbedUtil.defaultEmbed("Sup", Color.ORANGE)).setActionRows(ActionRow.of(
                    Button.secondary("idk", Emoji.fromMarkdown("<:first:930264043564961822>")),
                    Button.secondary("idak", Emoji.fromMarkdown("<:last:930264202331975701>"))
            )).queue();
        } else {
            event.getChannel().sendMessageEmbeds(EmbedUtil.defaultEmbed("Another message than the unique one", Color.ORANGE)).setActionRows(ActionRow.of(
                    Button.secondary("idk", Emoji.fromMarkdown("<:first:930264043564961822>")),
                    Button.secondary("idak", Emoji.fromMarkdown("<:last:930264202331975701>"))
            )).queue();
        }
    }
}
