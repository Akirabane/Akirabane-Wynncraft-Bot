package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Date;

public class Help implements ICommand {
    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {
        EmbedBuilder eb = new EmbedBuilder();
        Date date = new Date();

        int min = 0;
        int max = 255;
        int random_intA = (int)Math.floor(Math.random()*(max-min+1));
        int random_intB = (int)Math.floor(Math.random()*(max-min+1));
        int random_intC = (int)Math.floor(Math.random()*(max-min+1));

        eb.setTitle("Oukkie's Guild [UKK]", null);
        eb.setColor(new Color(random_intA, random_intB, random_intC));
        eb.setDescription("All commands in the server :");
        eb.addField("Command help", "§Help -> Shows this.", false);
        eb.addField("Command clear","§Clear <Amount> -> Will clear the set amount of messages.", false);
        eb.addField("Command guild", "§Guild <Guild's name> -> Will pop-up an embed with information about any guild.", false);
        eb.addField("Command info", "§Info <Player's name> -> Will pop-up an embed with all information about any player.", false);
        eb.addField("Command join", "§Join -> Will make the bot join the voice channel you are in.", false);
        eb.addField("Command Kick", "§Kick <Member's name> <reason> -> Will kick the member out from the discord server.", false);
        eb.addField("Command Ban", "§Ban <Member's name> <reason> -> Will ban the member out from the discord server.", false);
        eb.addField("Command Leave", "§Leave -> Will make the bot leave the voice channel you are in.", false);
        eb.addField("Command play", "§Play <youtube link> -> Will make the bot play the music you chose.", false);
        eb.addField("Command serverStatus", "§ServerStatus -> Will pop-op an embed with all information about Wynncraft server status.", false);
        eb.addField("Command stop", "§Stop -> Will make the bot stop playing music.", false);
        eb.addField("Command tps", "§Tps -> Will show the latency of the bot in MS.", false);
        eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
        eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
        eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
        eb.setFooter(date.toString());

        messageReceivedEvent.getMessage().getChannel().sendMessageEmbeds(eb.build()).queue();
        messageReceivedEvent.getMessage().delete().complete();
    }
}
