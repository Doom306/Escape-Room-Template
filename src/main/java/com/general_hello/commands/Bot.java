package com.general_hello.commands;

import com.general_hello.commands.OtherEvents.OnReadyEvent;
import com.general_hello.commands.OtherEvents.OnButtonClicked;
import com.general_hello.commands.commands.Commands.*;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.HashMap;

public class Bot {
    public static JDA jda;
    private static Bot bot;
    private final EventWaiter eventWaiter;
    // If you want to log stuff
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    // The lists and hashmap
    public static ArrayList<Location> locations = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<String> itemNames = new ArrayList<>();
    public static HashMap<String, Location> stringToLocation = new HashMap<>();
    public static HashMap<String, Item> stringToItem = new HashMap<>();
    public static HashMap<User, ArrayList<Item>> userInventory = new HashMap<>();
    public static ArrayList<String> locationNames = new ArrayList<>();

    public EventWaiter getEventWaiter() {
        return eventWaiter;
    }

    public static Bot getBot() {
        return bot;
    }

    public Bot() throws LoginException, InterruptedException {
        bot = this;
        // Initialize the waiter and client
        CommandClientBuilder client = new CommandClientBuilder();


        // Set the client settings
        client.setOwnerId(Config.get("owner_id"));
        client.setCoOwnerIds(Config.get("owner_id_partner"));
        client.setPrefix(Config.get("prefix"));
        client.useHelpBuilder(false);
        client.forceGuildOnly(Config.get("guild"));

        addCommands(client);
        eventWaiter = new EventWaiter();
        // Finalize the command client
        CommandClient commandClient = client.build();

        jda = JDABuilder.createDefault(Config.get("token"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(eventWaiter, commandClient,
                        new OnReadyEvent(), new OnButtonClicked())
                .setStatus(OnlineStatus.IDLE)
                .setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableCache(CacheFlag.ACTIVITY)
                .enableCache(CacheFlag.ONLINE_STATUS)
                .setActivity(Activity.listening("Escaping the room"))
                .build().awaitReady();
    }

    public static void main(String[] args) throws LoginException {
        try {
            new Bot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addCommands(CommandClientBuilder clientBuilder) {
        // commands here
        clientBuilder.addCommands(new GoToRoomCommand(), new OpenCommand(), new GiveCommand(), new ViewCommand());
    }

    public static void initialize() {
        // Edit this to add items or locations
        Item key = new Item("Key", "Here's your key");
        new Location("Door", textChannelFromId(962535632364777582L), "You unlocked the door with the key", key, "The door is locked");
        new Location("Red Door", textChannelFromId(962535673770950676L), "Welcome to the red door", key, "How do you think you'll open this");
    }

    // Method to get a text channel from its ID
    private static TextChannel textChannelFromId(long id) {
        return jda.getTextChannelById(id);
    }
}