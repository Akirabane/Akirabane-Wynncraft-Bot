package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import me.bed0.jWynn.WynncraftAPI;
import me.bed0.jWynn.api.v1.guild.WynncraftGuild;
import me.bed0.jWynn.api.v2.player.WynncraftPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Date;

public class Info implements ICommand {
    @Override
    public String getName() {
        return "Info";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {

        String[] args = messageReceivedEvent.getMessage().getContentRaw().split(" ");

        if(args.length != 2) {
            return;
        }

        if(isNumber(args[1])) {
            messageReceivedEvent.getChannel().sendMessage("Command Usage: §Info <Pseudo>").queue();
            return;
        }

        String NAME = args[1];

        WynncraftAPI api = new WynncraftAPI();
        WynncraftPlayer player = api.v2().player().stats(NAME).run()[0];
        WynncraftGuild guild = null;
        if(player.getGuild().getName() != null) {
            guild = api.v1().guildStats(player.getGuild().getName()).run();
        }

        EmbedBuilder eb = new EmbedBuilder();

        int min = 0;
        int max = 255;
        int random_intA = (int)Math.floor(Math.random()*(max-min+1));
        int random_intB = (int)Math.floor(Math.random()*(max-min+1));
        int random_intC = (int)Math.floor(Math.random()*(max-min+1));

        int year = (player.getMeta().getLastJoin().getYear() + 1900);
        int month = (player.getMeta().getLastJoin().getMonth() + 1);

        Date date = new Date();

        if(player.getMeta().getLocation().server == null) {
            eb.setTitle("Oukkie's Guild [UKK]", null);
            eb.setColor(new Color(random_intA, random_intB, random_intC));
            eb.setDescription("Informations of: " + player.getUsername());
            eb.addField("Location", "Not online", true);
            if(player.getMeta().getTag().getValue().getFriendlyName() == "") {
                eb.addField("Rank", "Player", true);
            } else {
                eb.addField("Rank", String.valueOf(player.getMeta().getTag().getValue().getFriendlyName()), true);
            }
            eb.addBlankField(true);
            eb.addField("Total combat levels", String.valueOf(player.getGlobal().getTotalLevel().getCombat() + "/1.484"), true);
            eb.addField("Chests opened", String.valueOf(player.getGlobal().getChestsFound()), true);
            eb.addBlankField(true);
            eb.addField("Total profession levels", String.valueOf(player.getGlobal().getTotalLevel().getProfession() + "/16.632"), true);
            if(player.getGuild().getName() == null) {
                eb.addField("Last day joined", String.valueOf(month + "/" + player.getMeta().getLastJoin().getDate() + "/" + year), true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
            }
            if(player.getGuild().getName() != null) {
                eb.addField("Guild", "[" + guild.getPrefix() + "]" + player.getGuild().getName(), true);
                eb.addBlankField(true);
                eb.addField("Last day joined", String.valueOf(month + "/" + player.getMeta().getLastJoin().getDate() + "/" + year), true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
            }

        } else {
            eb.setTitle("Oukkie's Guild [UKK]", null);
            eb.setColor(new Color(random_intA, random_intB, random_intC));
            eb.setDescription("Informations of: " + player.getUsername());
            eb.addField("Location", String.valueOf(player.getMeta().getLocation().server), true);
            if(player.getMeta().getTag().getValue().getFriendlyName() == "") {
                eb.addField("Rank", "Player", true);
            } else {
                eb.addField("Rank", String.valueOf(player.getMeta().getTag().getValue().getFriendlyName()), true);
            }
            eb.addBlankField(true);
            eb.addField("Total combat levels", String.valueOf(player.getGlobal().getTotalLevel().getCombat() + "/1484"), true);
            eb.addField("Chests opened", String.valueOf(player.getGlobal().getChestsFound()), true);
            eb.addBlankField(true);
            eb.addField("Total profession levels", String.valueOf(player.getGlobal().getTotalLevel().getProfession() + "/16.632"), true);
            if(player.getGuild().getName() == null) {
                eb.addField("Last day joined", String.valueOf(month + "/" + player.getMeta().getLastJoin().getDate() + "/" + year), true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
            }
            if(player.getGuild().getName() != null) {
                eb.addField("Guild", "[" + guild.getPrefix() + "]" + player.getGuild().getName(), true);
                eb.addBlankField(true);
                eb.addField("Last day joined", String.valueOf(month + "/" + player.getMeta().getLastJoin().getDate() + "/" + year), true);
                eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
                eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
                eb.setFooter(date.toString());
            }
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

