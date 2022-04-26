package fr.akirabane.wynnbot;

import ca.tristan.jdacommands.JDACommands;
import fr.akirabane.wynnbot.commands.*;
import fr.akirabane.wynnbot.events.EventHello;
import fr.akirabane.wynnbot.events.EventJoin;
import fr.akirabane.wynnbot.events.EventLeave;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.*;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.*;

public class Main {

    public static final GatewayIntent[] INTENTS = {GatewayIntent.GUILD_EMOJIS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES};
    public static final String TOKEN = "OTY3MDU5MzQyODk5Njc1MTQ4.YmKyMg.AG4md4DOYXLlGpVgZHGaF_Nxi7Y";

    public static void main(String[] args) throws LoginException {

        JDACommands jdaCommands = new JDACommands("§");
        jdaCommands.registerCommand(new Tps());
        jdaCommands.registerCommand(new Clear());
        jdaCommands.registerCommand(new Info());
        jdaCommands.registerCommand(new Guild());
        jdaCommands.registerCommand(new Kick());
        jdaCommands.registerCommand(new Ban());
        jdaCommands.registerCommand(new Join());
        jdaCommands.registerCommand(new Play());
        jdaCommands.registerCommand(new Stop());
        jdaCommands.registerCommand(new Leave());
        jdaCommands.registerCommand(new ServerStatus());
        jdaCommands.registerCommand(new Help());

        String[] messages={"Made by Akirabane","message 2"};
        int currentIndex=0;

        JDA jda = JDABuilder.create(TOKEN, Arrays.asList(INTENTS))
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.streaming(messages[currentIndex], "https://twitch.tv/AkirabaneVR"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(new EventJoin())
                .addEventListeners(new EventLeave())
                .addEventListeners(new EventHello())
                .addEventListeners(jdaCommands)
                .build();
        System.out.println("Bot successfully started.");
    }
}
