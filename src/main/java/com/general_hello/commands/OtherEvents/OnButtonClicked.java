package com.general_hello.commands.OtherEvents;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class OnButtonClicked extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        // Customize this to your liking.
        event.reply("You are supposed to program this part").setEphemeral(true).queue();
    }
}