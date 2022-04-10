package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;
import net.dv8tion.jda.api.entities.TextChannel;

public class Location {
    // Object for neatness with basic getters
    private final String name;
    private final TextChannel textChannel;
    private final String response;
    private final String errorResponse;
    private final Item itemRequired;

    public Location(String name, TextChannel textChannel, String response, Item itemRequired, String errorResponse) {
        this.name = name;
        this.textChannel = textChannel;
        this.response = response;
        this.itemRequired = itemRequired;
        this.errorResponse = errorResponse;
        Bot.locationNames.add(name);
        Bot.locations.add(this);
        Bot.stringToLocation.put(name, this);
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public String getName() {
        return name;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public String getResponse() {
        return response;
    }

    public Item getItemRequired() {
        return itemRequired;
    }
}
