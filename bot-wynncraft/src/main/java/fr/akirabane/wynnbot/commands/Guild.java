package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import me.bed0.jWynn.WynncraftAPI;
import me.bed0.jWynn.api.v1.guild.WynncraftGuild;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Guild implements ICommand {
    @Override
    public String getName() {
        return "Guild";
    }

    @Override
    public void execute(@NotNull MessageReceivedEvent messageReceivedEvent) {

        List<String> args = Arrays.asList(messageReceivedEvent.getMessage().getContentRaw().split(" "));

        if(args.size() < 2) {
            return;
        }

        if(isNumber(args.get(1))) {
            messageReceivedEvent.getChannel().sendMessage("Command Usage: §Guild <guild's name>").queue();
            return;
        }

        WynncraftAPI api = new WynncraftAPI();

        int min = 0;
        int max = 255;
        int random_intA = (int)Math.floor(Math.random()*(max-min+1));
        int random_intB = (int)Math.floor(Math.random()*(max-min+1));
        int random_intC = (int)Math.floor(Math.random()*(max-min+1));

        EmbedBuilder eb = new EmbedBuilder();
        Date date = new Date();

        switch (args.size()) {
            case 2:
                WynncraftGuild guild = api.v1().guildStats(args.get(1)).run();
                int year = (guild.getCreated().getYear() + 1900);
                int month = (guild.getCreated().getMonth() + 1);
                int day = guild.getCreated().getDate();
                eb.setTitle(guild.getName()+"'s Guild " + "[" + guild.getPrefix() + "]", null);
                eb.setColor(new Color(random_intA, random_intB, random_intC));
                eb.setDescription("Informations about: " + guild.getName());
                eb.addField("Members", String.valueOf(guild.getMembers().length), true);
                eb.addField("Level", String.valueOf(guild.getLevel()), true);
                eb.addBlankField(true);
                eb.addField("Current XP", String.valueOf(guild.getXp() +"%"), true);
                eb.addField("Territories", String.valueOf(guild.getTerritories()+"/406"), true);
                eb.addBlankField(true);
                eb.addField("Created on", month + "/" + day + "/" + year, true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
                break;

            case 3:
                WynncraftGuild guild2 = api.v1().guildStats(args.get(1) + " " + args.get(2)).run();
                int year2 = (guild2.getCreated().getYear() + 1900);
                int month2 = (guild2.getCreated().getMonth() + 1);
                int day2 = guild2.getCreated().getDate();
                eb.setTitle(guild2.getName()+"'s Guild " + "[" + guild2.getPrefix() + "]", null);
                eb.setColor(new Color(random_intA, random_intB, random_intC));
                eb.setDescription("Informations about: " + guild2.getName());
                eb.addField("Members", String.valueOf(guild2.getMembers().length), true);
                eb.addField("Level", String.valueOf(guild2.getLevel()), true);
                eb.addBlankField(true);
                eb.addField("Current XP", String.valueOf(guild2.getXp() +"%"), true);
                eb.addField("Territories", String.valueOf(guild2.getTerritories()+"/406"), true);
                eb.addBlankField(true);
                eb.addField("Created on", month2 + "/" + day2 + "/" + year2, true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
                break;

            case 4:
                WynncraftGuild guild3 = api.v1().guildStats(args.get(1) + " " + args.get(2) + " " + args.get(3)).run();
                int year3 = (guild3.getCreated().getYear() + 1900);
                int month3 = (guild3.getCreated().getMonth() + 1);
                int day3 = guild3.getCreated().getDate();
                eb.setTitle(guild3.getName()+"'s Guild " + "[" + guild3.getPrefix() + "]", null);
                eb.setColor(new Color(random_intA, random_intB, random_intC));
                eb.setDescription("Informations about: " + guild3.getName());
                eb.addField("Members", String.valueOf(guild3.getMembers().length), true);
                eb.addField("Level", String.valueOf(guild3.getLevel()), true);
                eb.addBlankField(true);
                eb.addField("Current XP", String.valueOf(guild3.getXp() +"%"), true);
                eb.addField("Territories", String.valueOf(guild3.getTerritories()+"/406"), true);
                eb.addBlankField(true);
                eb.addField("Created on", month3 + "/" + day3 + "/" + year3, true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
                break;

            case 5:
                WynncraftGuild guild4 = api.v1().guildStats(args.get(1) + " " + args.get(2) + " " + args.get(3) + " " + args.get(4)).run();
                int year4 = (guild4.getCreated().getYear() + 1900);
                int month4 = (guild4.getCreated().getMonth() + 1);
                int day4 = guild4.getCreated().getDate();
                eb.setTitle(guild4.getName()+"'s Guild " + "[" + guild4.getPrefix() + "]", null);
                eb.setColor(new Color(random_intA, random_intB, random_intC));
                eb.setDescription("Informations about: " + guild4.getName());
                eb.addField("Members", String.valueOf(guild4.getMembers().length), true);
                eb.addField("Level", String.valueOf(guild4.getLevel()), true);
                eb.addBlankField(true);
                eb.addField("Current XP", String.valueOf(guild4.getXp() +"%"), true);
                eb.addField("Territories", String.valueOf(guild4.getTerritories()+"/406"), true);
                eb.addBlankField(true);
                eb.addField("Created on", month4 + "/" + day4 + "/" + year4, true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
                break;

            default:
                messageReceivedEvent.getChannel().sendMessage("Command Usage: §Guild <guild's name>").queue();
                break;
        }

        messageReceivedEvent.getMessage().getChannel().sendMessageEmbeds(eb.build()).queue();
        messageReceivedEvent.getMessage().delete().complete();

    }

    private boolean isNumber(String msg) {
        try {
            Integer.parseInt(msg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
