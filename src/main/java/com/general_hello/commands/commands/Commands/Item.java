package com.general_hello.commands.commands.Commands;

import com.general_hello.commands.Bot;

public class Item {
    // Object for neatness with basic getters

    private final String name;
    private final String response;

    public Item(String name, String response) {
        this.name = name;
        this.response = response;
        Bot.itemNames.add(name);
        Bot.items.add(this);
        Bot.stringToItem.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String getResponse() {
        return response;
    }
}
