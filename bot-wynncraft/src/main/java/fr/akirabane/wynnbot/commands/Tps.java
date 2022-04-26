package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Tps implements ICommand {
    @Override
    public String getName() {
        return "Tps";
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        channel.sendMessage("Wait...")
                .queue(res -> {
                    res.editMessageFormat("TPS: %d ms", System.currentTimeMillis() - time).queue();
                });
        event.getMessage().delete().complete();
    }
}
