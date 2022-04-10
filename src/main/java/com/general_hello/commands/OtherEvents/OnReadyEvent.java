package com.general_hello.commands.OtherEvents;

import com.general_hello.commands.Bot;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnReadyEvent extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnReadyEvent.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // Basically the most important thing to store
        Bot.jda = event.getJDA();
        // Initialize the items and locations
        Bot.initialize();
        // Some logging output
        LOGGER.info("Bot is started and running!");
    }
}
